# 건국대학교 메타버스 게임 서버 🚀

> **Spring Boot 기반의 실시간 멀티플레이어 WebSocket 서버**

건국대학교 메타버스 웹 게임의 백엔드 서버로, STOMP WebSocket을 통한 실시간 통신과 MySQL 데이터베이스를 활용한 사용자 데이터 관리를 제공합니다.


## Architecture
![Web App Reference Architecture (2)](https://github.com/KidoMeta/BackEnd/assets/83938394/c6292d78-7eb2-4f64-b908-3016b8be02aa)


## 🛠️ 기술 스택

### Backend Framework
- **Spring Boot 3.2.4** - 최신 Java 엔터프라이즈 프레임워크
- **Java 21** - LTS 버전의 최신 Java
- **Gradle** - 빌드 도구 및 의존성 관리

### 실시간 통신
- **Spring WebSocket** - WebSocket 서버 구현
- **STOMP (Simple Text Oriented Messaging Protocol)** - 메시지 브로커
- **SockJS** - WebSocket 폴백 지원

### 데이터베이스
- **Spring Data JPA** - ORM 및 데이터 액세스
- **MySQL** - 프로덕션 데이터베이스
- **H2** - 개발/테스트용 인메모리 데이터베이스
- **ConcurrentHashMap** - 실시간 사용자 상태 메모리 캐시

### 개발 도구
- **Lombok** - 보일러플레이트 코드 자동 생성
- **Docker** - 컨테이너화 배포

## 🏗️ 아키텍처

### 레이어드 아키텍처
```
Controller Layer    → REST/WebSocket 엔드포인트
Service Layer       → 비즈니스 로직
Repository Layer    → 데이터 액세스
Entity Layer        → 도메인 모델
```

### 실시간 통신 구조
```
Client ↔ WebSocket ↔ STOMP Handler ↔ Message Controller ↔ Service ↔ Repository
```

## 📡 API 엔드포인트

### WebSocket 연결
- **엔드포인트**: `/ws`
- **프로토콜**: STOMP over WebSocket
- **CORS**: 모든 오리진 허용 (`*`)

### 맵/플레이어 관리
| 엔드포인트 | 설명 | 요청 | 응답 |
|-----------|------|------|------|
| `/app/map/new` | 새 플레이어 생성 | `ReqNewUserDto` | `UserEntity` |
| `/app/map/move` | 플레이어 이동 | `ReqMoveUserDto` | `ResMoveUserDto` |
| `/app/map/stop` | 플레이어 정지 | `ReqStopUserDto` | `ResStopUserDto` |
| `/app/map/all` | 모든 플레이어 조회 | - | `List<UserEntity>` |

### 채팅 시스템
| 엔드포인트 | 설명 | 요청 | 응답 |
|-----------|------|------|------|
| `/app/chat/message` | 채팅 메시지 전송 | `ReqChatMessageDto` | `ResChatMessageDto` |
| `/app/chat/messages` | 채팅 기록 조회 | - | `ResChatMessageDto[]` |

### 구독 토픽
| 토픽 | 설명 |
|------|------|
| `/topic/map/new` | 새 플레이어 입장 알림 |
| `/topic/map/move` | 플레이어 이동 브로드캐스트 |
| `/topic/map/remove` | 플레이어 퇴장 알림 |
| `/topic/chat/message` | 실시간 채팅 메시지 |

## 🗄️ 데이터베이스 설계

### UserEntity (MySQL)
```sql
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45),
    x INT,
    y INT,
    direction VARCHAR(10)
);
```

### ChatEntity (MySQL)
```sql
CREATE TABLE chat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    text TEXT,
    username VARCHAR(45)
);
```

### MemoryUserEntity (메모리)
- **목적**: 실시간 사용자 상태 관리
- **저장소**: `ConcurrentHashMap`
- **필드**: id, username, sessionId, x, y, direction

## ⚡ 핵심 기능 구현

### 1. 하이브리드 저장소 전략
```java
// 접속 중: 메모리 저장소 (빠른 읽기/쓰기)
memoryUserRepository.save(user);

// 접속 종료: MySQL 영구 저장
userRepository.save(user);
```

### 2. 실시간 상태 동기화
```java
@MessageMapping("/move")
@SendTo("/topic/map/move")
public ResMoveUserDto moveUser(ReqMoveUserDto position) {
    return mapService.moveUser(position);
}
```

### 3. 세션 관리 시스템
```java
@EventListener
public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
    messagingTemplate.convertAndSend("/topic/map/remove", 
        mapService.removeUser(sessionId));
}
```

### 4. 채팅 히스토리 관리
```java
// 최근 20개 메시지 조회 (역순으로 정렬 후 뒤집기)
List<ChatEntity> chatEntities = chatRepository.findTop20ByOrderByIdDesc();
Collections.reverse(chatEntities);
```

## 🚀 시작하기

### 개발 환경 설정
```bash
# 프로젝트 클론
git clone <repository-url>
cd Server

# 빌드 및 실행
./gradlew build
./gradlew bootRun
```

### Docker 배포
```bash
# 빌드
./gradlew build

# Docker 이미지 빌드
docker build -t metaverse-server .

# 컨테이너 실행
docker run -p 3000:3000 metaverse-server
```

### 환경 설정

#### application.properties
```properties
# 서버 포트
server.port=3000

# MySQL 설정
spring.datasource.url=jdbc:mysql://localhost:3306/getaguitar
spring.datasource.username=root
spring.datasource.password=password

# JPA 설정
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 📊 성능 최적화

### 메모리 캐싱
- **ConcurrentHashMap**: 동시성 안전한 메모리 저장소
- **ThreadLocal**: 세션별 컨텍스트 관리
- **빠른 조회**: O(1) 사용자 상태 접근

### 데이터베이스 최적화
- **Connection Pooling**: HikariCP 기본 설정
- **Lazy Loading**: JPA 지연 로딩 활용
- **인덱싱**: username, sessionId 필드 최적화

### WebSocket 최적화
- **Message Broker**: 효율적인 pub/sub 패턴
- **Session Management**: 자동 연결 해제 처리
- **CORS 설정**: 개발 환경 유연성

## 🔧 주요 기술적 특징

### 1. 이벤트 기반 아키텍처
- **WebSocket Events**: 연결/해제 자동 처리
- **Message Mapping**: STOMP 메시지 라우팅
- **브로드캐스팅**: 실시간 상태 전파

### 2. 레이어 분리 설계
- **Controller**: WebSocket 메시지 처리
- **Service**: 비즈니스 로직 캡슐화
- **Repository**: 데이터 액세스 추상화

### 3. DTO 패턴 활용
- **요청/응답 분리**: Req/Res DTO 구조
- **데이터 검증**: Bean Validation
- **API 버전 관리**: 호환성 보장

### 4. 스레드 안전성
- **ConcurrentHashMap**: 동시 액세스 안전
- **ThreadLocal**: 세션별 격리
- **@Transactional**: 데이터 일관성

## 🌐 배포 및 운영

### 프로덕션 설정
- **포트**: 3000 (기본값)
- **데이터베이스**: MySQL 8.0+
- **JVM**: OpenJDK 21
- **컨테이너**: Docker + Alpine Linux

### 모니터링
- **로깅**: SLF4J + Logback
- **헬스체크**: Spring Actuator
- **메트릭**: JVM 및 애플리케이션 메트릭

---
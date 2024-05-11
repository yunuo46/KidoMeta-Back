package getaguitar.site.demo.Repository;

import getaguitar.site.demo.Entity.MemoryUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemoryUserRepository {
    void save(MemoryUserEntity user);
    Optional<MemoryUserEntity> findByUsername(String username);
    Optional<MemoryUserEntity> findBySessionId(String sessionId);
    List<MemoryUserEntity> findAll();
    void deleteBySessionId(String sessionId);
    void updateX(String username, int x);
    void updateY(String username, int y);
}

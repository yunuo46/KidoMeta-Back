package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.MoveUser.ReqMoveUserDto;
import getaguitar.site.demo.Dto.MoveUser.ResMoveUserDto;
import getaguitar.site.demo.Dto.StopUser.ReqStopUserDto;
import getaguitar.site.demo.Dto.StopUser.ResStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;

import getaguitar.site.demo.Entity.MemoryUserEntity;
import getaguitar.site.demo.Entity.UserEntity;
import getaguitar.site.demo.Repository.MemoryUserRepository;
import getaguitar.site.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MapServiceImpl implements MapService {
    private final UserRepository userRepository;
    private final MemoryUserRepository memoryUserRepository;

    public MapServiceImpl(UserRepository userRepository, MemoryUserRepository memoryUserRepository) {
        this.userRepository = userRepository;
        this.memoryUserRepository = memoryUserRepository;
    }

    @Override
    public UserEntity createUser(String sessionId, ReqNewUserDto newUser) {
        UserEntity user = userRepository.save(UserEntity.builder()
                .username(newUser.getUsername())
                .x(400)
                .y(300)
                .direction("down")
                .build());
        int id = user.getId();
        memoryUserRepository.save(new MemoryUserEntity(id,newUser.getUsername(),sessionId,400,300,"down"));
        return user;
    }

    @Override
    public List<Optional<UserEntity>> getAllUser() {
        List<MemoryUserEntity> users = memoryUserRepository.findAll();
        List<Optional<UserEntity>> result = new ArrayList<>();
        for (MemoryUserEntity user : users) {
            UserEntity userEntity = userRepository.save(UserEntity.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .x(user.getX())
                    .y(user.getY())
                    .direction(user.getDirection())
                    .build());
            result.add(Optional.of(userEntity));
        }
        return result;
    }

    @Override
    public String removeUser(String sessionId) {
        // remove memoryRepo
        MemoryUserEntity user = memoryUserRepository.findBySessionId(sessionId).orElseThrow();
        String username = user.getUsername();
        memoryUserRepository.deleteBySessionId(sessionId);

        // add RDS
        userRepository.save(UserEntity.builder()
                .id(user.getId())
                .username(username)
                .x(user.getX())
                .y(user.getY())
                .direction(user.getDirection())
                .build());

        return username;
    }

    @Override
    @Transactional
    public ResMoveUserDto moveUser(ReqMoveUserDto userInfo) {
        String username = userInfo.getUsername();
        String direction = userInfo.getDirection();
        MemoryUserEntity user = memoryUserRepository.findByUsername(username).orElseThrow();

        switch (direction) {
            case "up", "down" -> {
                user.setY(userInfo.getY());
            }
            case "left", "right" -> {
                user.setX(userInfo.getX());
            }
        }
        return new ResMoveUserDto(username, user.getX(), user.getY(), direction);
    }

    @Override
    public ResStopUserDto stopUser(ReqStopUserDto stopUser) {
        String username = stopUser.getUsername();
        MemoryUserEntity user = memoryUserRepository.findByUsername(username).orElseThrow();
        return new ResStopUserDto(username, user.getX(), user.getY());
    }
}

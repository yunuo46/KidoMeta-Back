package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.MoveUser.MoveUserDto;
import getaguitar.site.demo.Dto.RemoveUser.ResRemoveUserDto;
import getaguitar.site.demo.Dto.StopUser.ReqStopUserDto;
import getaguitar.site.demo.Dto.StopUser.ResStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;

import getaguitar.site.demo.Entity.UserEntity;
import getaguitar.site.demo.Interceptor.StompHandler;
import getaguitar.site.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapServiceImpl implements MapService {
    private final UserRepository userRepository;

    public MapServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(String sessionId, ReqNewUserDto newUser) {
        return userRepository.save(UserEntity.builder()
                .sessionId(sessionId)
                .username(newUser.getUsername())
                .x(400)
                .y(300)
                .direction("down")
                .build());
    }

    @Override
    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public String removeUser(String sessionId) {
        String username = userRepository.findBySessionId(sessionId).getUsername();
        return username;
    }

    @Override
    @Transactional
    public MoveUserDto moveUser(MoveUserDto userInfo) {
        int id = userInfo.getId();
        UserEntity user = userRepository.findById(id).orElseThrow();

        String username = userInfo.getUsername();
        int x = userInfo.getX();
        int y = userInfo.getY();
        String direction = userInfo.getDirection();

        switch (direction) {
            case "up" -> {
                y -= 4;
                user.setY(y);
            }
            case "down" -> {
                y += 4;
                user.setY(y);
            }
            case "left" -> {
                x -= 4;
                user.setX(x);
            }
            case "right" -> {
                x += 4;
                user.setX(x);
            }
        }
        return new MoveUserDto(id, username, x, y, direction);
    }

    @Override
    public ResStopUserDto stopUser(ReqStopUserDto stopUser) {
        System.out.println("stop");
        String username = stopUser.getUsername();
        int id = stopUser.getId();
        UserEntity user = userRepository.findById(id).orElseThrow();
        return new ResStopUserDto(username, user.getX(), user.getY());
    }
}

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
    private int x;
    private int y;

    public MapServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(String sessionId, ReqNewUserDto newUser) {
        x=400;
        y=300;
        return userRepository.save(UserEntity.builder()
                .sessionId(sessionId)
                .username(newUser.getUsername())
                .x(x)
                .y(y)
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
        userRepository.delete(userRepository.findBySessionId(sessionId));
        return username;
    }

    @Override
    @Transactional
    public MoveUserDto moveUser(MoveUserDto userInfo) {
        String username = userInfo.getUsername();
        String direction = userInfo.getDirection();

        switch (direction) {
            case "up" -> {
                y -= 4;
            }
            case "down" -> {
                y += 4;
            }
            case "left" -> {
                x -= 4;
            }
            case "right" -> {
                x += 4;
            }
        }
        return new MoveUserDto(username, x, y, direction);
    }

    @Override
    public ResStopUserDto stopUser(ReqStopUserDto stopUser) {
        String username = stopUser.getUsername();
        int id = stopUser.getId();
        userRepository.save(UserEntity.builder()
                .id(id).x(x).y(y).build());
        return new ResStopUserDto(username, x, y);
    }
}

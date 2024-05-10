package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.MoveUser.MoveUserDto;
import getaguitar.site.demo.Dto.StopUser.ReqStopUserDto;
import getaguitar.site.demo.Dto.StopUser.ResStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;
import getaguitar.site.demo.Dto.NewUser.ResNewUserDto;

import getaguitar.site.demo.Entity.UserEntity;
import getaguitar.site.demo.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapServiceImpl implements MapService {
    private final UserRepository userRepository;

    public MapServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(ReqNewUserDto newUser){
        int id = newUser.getId();
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public MoveUserDto moveUser(MoveUserDto userInfo) {
        System.out.println("move");
        int id = userInfo.getId();
        UserEntity user = userRepository.findById(id).orElseThrow();

        String username = userInfo.getUsername();
        int x = userInfo.getX();
        int y = userInfo.getY();
        String direction = userInfo.getDirection();

        switch (direction) {
            case "up" -> {
                y -= 2;
                user.setY(y);
            }
            case "down" -> {
                y += 2;
                user.setY(y);
            }
            case "left" -> {
                x -= 2;
                user.setX(x);
            }
            case "right" -> {
                x += 2;
                user.setX(x);
            }
        }
        return new MoveUserDto(id, username, x, y, direction);
    }

    @Override
    public ResStopUserDto stopUser(ReqStopUserDto stopUser){
        System.out.println("stop");
        String username = stopUser.getUsername();
        int id = stopUser.getId();
        UserEntity user= userRepository.findById(id).orElseThrow();
        return new ResStopUserDto(username, user.getX(), user.getY());
    }
}

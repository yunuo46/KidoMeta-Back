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
    public ResNewUserDto createUser(ReqNewUserDto newUser){
        String username = userRepository.save(UserEntity.builder()
                .username(newUser.getUsername())
                .x(400)
                .y(300)
                .direction("down")
                .build()).getUsername();
        return new ResNewUserDto(username,400,300,"down");
    }

    @Override
    public List<UserEntity> getAllUser(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public MoveUserDto moveUser(MoveUserDto position) {
        String username = position.getUsername();
        UserEntity user = userRepository.findByUsername(username);

        int x = position.getX();
        int y = position.getY();
        String direction = position.getDirection();

        if(direction.equals("up")) { y-=2; user.setY(y); }
        else if(direction.equals("down")) { y+=2; user.setY(y); }
        else if(direction.equals("left")) { x-=2; user.setX(x); }
        else if(direction.equals("right")) { x+=2; user.setX(x); }

        return new MoveUserDto(username, x, y, direction);
    }

    @Override
    public ResStopUserDto stopUser(ReqStopUserDto stopUser){
        String username = stopUser.getUsername();
        UserEntity user = userRepository.findByUsername(username);
        ResStopUserDto resStopUserDto = new ResStopUserDto(username, user.getX(), user.getY());
        return resStopUserDto;
    }
}

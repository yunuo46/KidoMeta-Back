package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.MoveUser.ResMoveUserDto;
import getaguitar.site.demo.Dto.StopUser.ReqStopUserDto;
import getaguitar.site.demo.Dto.StopUser.ResStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;
import getaguitar.site.demo.Dto.NewUser.ResNewUserDto;

import getaguitar.site.demo.Entity.UserEntity;
import getaguitar.site.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MapServiceImpl implements MapService {
    private final UserRepository userRepository;

    public MapServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResNewUserDto createUser(ReqNewUserDto newUser){
        String username = userRepository.save(UserEntity.builder().username("TestName").x(0).y(0).build()).getUsername();
        ResNewUserDto resNewUserDto = new ResNewUserDto(username,0,0,"down");
        return resNewUserDto;
    }

    @Override
    public ResMoveUserDto moveUser(String direction) {
        Optional<UserEntity> user = Optional.of(userRepository.findById(1).get());
        UserEntity userEntity = user.get();

        int x = userEntity.getX();
        int y = userEntity.getY();
        if(direction.equals("up")) y++;
        else if(direction.equals("down")) y--;
        else if(direction.equals("left")) x--;
        else if(direction.equals("right")) x++;

        UserEntity storedUser = userRepository.save(UserEntity.builder()
                        .id(userEntity.getId())
                        .x(x)
                        .y(y)
                        .build());

        ResMoveUserDto moveUserDto = new ResMoveUserDto(userEntity.getUsername(), storedUser.getX(), storedUser.getY(), direction);
        return moveUserDto;
    }

    @Override
    public ResStopUserDto stopUser(ReqStopUserDto stopUser){
        ResStopUserDto resStopUserDto = new ResStopUserDto(stopUser.getUsername(), 0, 0);
        return resStopUserDto;
    }
}

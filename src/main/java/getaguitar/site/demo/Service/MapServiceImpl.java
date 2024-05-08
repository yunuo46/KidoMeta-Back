package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.ReqNewUserDto;
import getaguitar.site.demo.Dto.ResMoveUserDto;
import getaguitar.site.demo.Dto.ResNewUserDto;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {
    @Override
    public ResNewUserDto createUser(ReqNewUserDto newUser){
        ResNewUserDto resNewUserDto = new ResNewUserDto("testId",0,0,"down");
        return resNewUserDto;
    }

    @Override
    public ResMoveUserDto moveUser(String direction) {
        ResMoveUserDto moveUserDto = new ResMoveUserDto("testId", 1, 1, direction);
        return moveUserDto;
    }

}

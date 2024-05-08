package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.ReqNewUserDto;
import getaguitar.site.demo.Dto.ReqStopUserDto;
import getaguitar.site.demo.Dto.ResNewUserDto;
import getaguitar.site.demo.Dto.ResStopUserDto;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {
    @Override
    public ResNewUserDto createUser(ReqNewUserDto newUser){
        ResNewUserDto resNewUserDto = new ResNewUserDto("testId",0,0,"down");
        return resNewUserDto;
    }

    public ResStopUserDto stopUser(ReqStopUserDto stopUser){
        ResStopUserDto resStopUserDto = new ResStopUserDto("testId", 0, 0);
        return resStopUserDto;
    }
}

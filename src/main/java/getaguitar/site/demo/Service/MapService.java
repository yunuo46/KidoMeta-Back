package getaguitar.site.demo.Service;

import getaguitar.site.demo.Dto.ReqStopUserDto;
import getaguitar.site.demo.Dto.ResStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;
import getaguitar.site.demo.Dto.NewUser.ResNewUserDto;

public interface MapService {
    ResNewUserDto createUser(ReqNewUserDto newUser);
    ResStopUserDto stopUser(ReqStopUserDto stopUser);
}

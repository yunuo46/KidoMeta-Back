package getaguitar.site.demo.Service;

import getaguitar.site.demo.Dto.ReqNewUserDto;
import getaguitar.site.demo.Dto.ReqStopUserDto;
import getaguitar.site.demo.Dto.ResNewUserDto;
import getaguitar.site.demo.Dto.ResStopUserDto;

public interface MapService {
    ResNewUserDto createUser(ReqNewUserDto newUser);
    ResStopUserDto stopUser(ReqStopUserDto stopUser);
}

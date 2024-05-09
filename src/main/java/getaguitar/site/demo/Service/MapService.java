package getaguitar.site.demo.Service;

import getaguitar.site.demo.Dto.MoveUser.ResMoveUserDto;
import getaguitar.site.demo.Dto.StopUser.ReqStopUserDto;
import getaguitar.site.demo.Dto.StopUser.ResStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;
import getaguitar.site.demo.Dto.NewUser.ResNewUserDto;

public interface MapService {
    ResNewUserDto createUser(ReqNewUserDto newUser);
    ResStopUserDto stopUser(ReqStopUserDto stopUser);
    ResMoveUserDto moveUser(String direction);
}

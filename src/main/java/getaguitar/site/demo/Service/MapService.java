package getaguitar.site.demo.Service;

import getaguitar.site.demo.Dto.MoveUser.MoveUserDto;
import getaguitar.site.demo.Dto.StopUser.ReqStopUserDto;
import getaguitar.site.demo.Dto.StopUser.ResStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;
import getaguitar.site.demo.Dto.NewUser.ResNewUserDto;
import getaguitar.site.demo.Entity.UserEntity;

import java.util.List;

public interface MapService {
    ResNewUserDto createUser(ReqNewUserDto newUser);
    ResStopUserDto stopUser(ReqStopUserDto stopUser);
    MoveUserDto moveUser(MoveUserDto moveUser);
    List<UserEntity> getAllUser();
}

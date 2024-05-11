package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.MoveUser.ReqMoveUserDto;
import getaguitar.site.demo.Dto.MoveUser.ResMoveUserDto;
import getaguitar.site.demo.Dto.StopUser.ReqStopUserDto;
import getaguitar.site.demo.Dto.StopUser.ResStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;
import getaguitar.site.demo.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface MapService {
    UserEntity createUser(String sessionId, ReqNewUserDto newUser);
    ResStopUserDto stopUser(ReqStopUserDto stopUser);
    ResMoveUserDto moveUser(ReqMoveUserDto moveUser);
    List<Optional<UserEntity>> getAllUser();
    String removeUser(String sessionId);
}

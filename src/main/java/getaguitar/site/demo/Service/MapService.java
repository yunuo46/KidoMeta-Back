package getaguitar.site.demo.Service;

import getaguitar.site.demo.Dto.ReqNewUserDto;
import getaguitar.site.demo.Dto.ResMoveUserDto;
import getaguitar.site.demo.Dto.ResNewUserDto;

public interface MapService {
    ResNewUserDto createUser(ReqNewUserDto newUser);
    ResMoveUserDto moveUser(String direction);
}

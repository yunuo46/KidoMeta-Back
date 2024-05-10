package getaguitar.site.demo.Service;

import getaguitar.site.demo.Dto.RemoveUser.ResRemoveUserDto;

public interface UserService {
    ResRemoveUserDto removeUser(String sessionId);
}

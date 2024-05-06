package getaguitar.site.demo.Service;

import getaguitar.site.demo.Dto.UserDto;

public interface APIService {
    UserDto getUser(String username);
}

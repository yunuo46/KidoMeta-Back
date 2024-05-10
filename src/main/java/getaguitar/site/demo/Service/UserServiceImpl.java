package getaguitar.site.demo.Service;

import getaguitar.site.demo.Dto.RemoveUser.ResRemoveUserDto;
import getaguitar.site.demo.Repository.UserRepository;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResRemoveUserDto removeUser(String sessionId) {
        return new ResRemoveUserDto(sessionId);
    }
}

package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class APIServiceImpl implements APIService{
    @Override
    public UserDto getUser(String username){
        UserDto userDto = new UserDto(username);
        return userDto;
    }
}

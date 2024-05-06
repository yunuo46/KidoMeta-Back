package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.ResUserNameDto;
import org.springframework.stereotype.Service;

@Service
public class APIServiceImpl implements APIService{
    @Override
    public ResUserNameDto getUser(String username){
        ResUserNameDto userDto = new ResUserNameDto(username);
        return userDto;
    }
}

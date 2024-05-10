package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.ResUserNameDto;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

@Service
public class APIServiceImpl implements APIService{
    @Override
    public ResUserNameDto getUser(){
        String username = RandomStringUtils.randomAlphanumeric(5);
        ResUserNameDto userDto = new ResUserNameDto(username);
        return userDto;
    }
}

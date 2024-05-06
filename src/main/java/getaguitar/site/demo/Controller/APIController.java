package getaguitar.site.demo.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import getaguitar.site.demo.Dto.UserDto;
import getaguitar.site.demo.Service.APIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {

    private final APIService apiService;

    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/user")
    public UserDto getUser() throws JsonProcessingException {
        UserDto userDto = apiService.getUser("TestName");
        return userDto;
    }
}

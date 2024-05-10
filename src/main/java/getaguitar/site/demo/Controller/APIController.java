package getaguitar.site.demo.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import getaguitar.site.demo.Dto.ResUserDto;
import getaguitar.site.demo.Service.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class APIController {

    private final APIService apiService;

    @GetMapping("/user")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResUserDto getUser() throws JsonProcessingException {
        return apiService.getUser();
    }
}

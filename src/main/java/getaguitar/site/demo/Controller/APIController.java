package getaguitar.site.demo.Controller;
import getaguitar.site.demo.Dto.ResUserDto;
import getaguitar.site.demo.Service.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
@RequestMapping("/api")
public class APIController {

    private final APIService apiService;

    @GetMapping("/user")
    public ResUserDto getUser() {
        return apiService.getUser();
    }
}

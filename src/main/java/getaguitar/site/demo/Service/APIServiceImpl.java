package getaguitar.site.demo.Service;
import getaguitar.site.demo.Dto.ResUserDto;
import getaguitar.site.demo.Entity.UserEntity;
import getaguitar.site.demo.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

@Service
public class APIServiceImpl implements APIService{
    private final UserRepository userRepository;
    public APIServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResUserDto getUser(){
        String username = RandomStringUtils.randomAlphanumeric(5);
        int id = userRepository.save(UserEntity.builder()
                .username(username)
                .x(400)
                .y(300)
                .direction("down")
                .build()).getId();
        return new ResUserDto(id, username);
    }
}

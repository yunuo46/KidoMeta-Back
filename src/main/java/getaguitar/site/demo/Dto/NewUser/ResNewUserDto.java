package getaguitar.site.demo.Dto.NewUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResNewUserDto {
    private int id;
    private String username;
    private int x;
    private int y;
    private String direction;
}

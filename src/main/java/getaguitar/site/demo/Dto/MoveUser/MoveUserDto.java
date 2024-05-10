package getaguitar.site.demo.Dto.MoveUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoveUserDto {
    private int id;
    private String username;
    private int x;
    private int y;
    private String direction;
}

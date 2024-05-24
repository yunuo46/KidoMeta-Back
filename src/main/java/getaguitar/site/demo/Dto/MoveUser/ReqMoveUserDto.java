package getaguitar.site.demo.Dto.MoveUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqMoveUserDto {
    private String username;
    private String direction;
    private int x;
    private int y;
}

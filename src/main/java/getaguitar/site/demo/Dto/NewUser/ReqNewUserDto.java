package getaguitar.site.demo.Dto.NewUser;

import getaguitar.site.demo.Dto.IPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReqNewUserDto {
    private String username;
    private String room;
    private IPosition position;
}
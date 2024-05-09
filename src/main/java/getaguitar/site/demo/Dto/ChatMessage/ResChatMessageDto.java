package getaguitar.site.demo.Dto.ChatMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResChatMessageDto {
    private String text;
    private String username;
}

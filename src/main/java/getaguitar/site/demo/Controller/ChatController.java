package getaguitar.site.demo.Controller;

import getaguitar.site.demo.Dto.ResChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MessageMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/topic/chat/message")
    public ResChatMessageDto sendMessage(ResChatMessageDto message) {
        return message;
    }
}

package getaguitar.site.demo.Controller;

import getaguitar.site.demo.Dto.ChatMessage.ReqChatMessageDto;
import getaguitar.site.demo.Dto.ChatMessage.ResChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@MessageMapping("/chat")
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/topic/chat/message")
    public ResChatMessageDto sendMessage(@Payload ReqChatMessageDto message) {
        return new ResChatMessageDto(message.getText(), message.getUsername());
    }
}

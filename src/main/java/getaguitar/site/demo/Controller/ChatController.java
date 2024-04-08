package getaguitar.site.demo.Controller;

import getaguitar.site.demo.Model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MessageMapping("chat")
@RequiredArgsConstructor
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/topic/chat/message")
    public ChatMessage sendMessage(ChatMessage message) {
        return message;
    }
}

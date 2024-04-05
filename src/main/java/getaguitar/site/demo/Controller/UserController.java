package getaguitar.site.demo.Controller;

import getaguitar.site.demo.Model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final SimpMessageSendingOperations simpleMessageSendingOperations;

    @MessageMapping("/message")
    @SendTo("/channel")
    public void sendMessage(ChatMessage message) {

        System.out.println(message);
        //구독중인 client에 메세지를 보낸다.
        simpleMessageSendingOperations.convertAndSend("/channel", message);
    }
}

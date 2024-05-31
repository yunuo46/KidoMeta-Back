package getaguitar.site.demo.Controller;

import getaguitar.site.demo.Dto.ChatMessage.ReqChatMessageDto;
import getaguitar.site.demo.Dto.ChatMessage.ResChatMessageDto;
import getaguitar.site.demo.Entity.ChatEntity;
import getaguitar.site.demo.Repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@MessageMapping("/chat")
public class ChatController {

    private final ChatRepository chatRepository;

    @MessageMapping("/message")
    @SendTo("/topic/chat/message")
    public ResChatMessageDto sendMessage(@Payload ReqChatMessageDto message) {
        String text = message.getText();
        String username = message.getUsername();
        ChatEntity chatEntity = new ChatEntity(null, text, username);

        chatRepository.save(chatEntity);
        return new ResChatMessageDto(text,username);
    }

    @MessageMapping("/messages")
    @SendTo("/topic/chat/messages")
    public ResChatMessageDto[] sendMessages() {
        // 최근 순으로 10개의 메시지를 조회
        List<ChatEntity> chatEntities = chatRepository.findTop10ByOrderByIdDesc();

        // ChatEntity 리스트를 ResChatMessageDto 배열로 변환
        ResChatMessageDto[] resChatMessageDtos = chatEntities.stream()
                .map(chatEntity -> new ResChatMessageDto(chatEntity.getText(), chatEntity.getUsername()))
                .toArray(ResChatMessageDto[]::new);

        return resChatMessageDtos;
    }
}

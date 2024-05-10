package getaguitar.site.demo.Controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;


@RestController
@RequiredArgsConstructor
public class StompController {
    private static final Logger LOGGER = LoggerFactory.getLogger( StompController.class );

    private final SimpMessageSendingOperations simpleMessageSendingOperations;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccesor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccesor.getSessionId();
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccesor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccesor.getSessionId();
    }
}

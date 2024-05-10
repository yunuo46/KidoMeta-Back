package getaguitar.site.demo.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET})
public class StompHandler implements ChannelInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger( StompHandler.class );

    @Override
    public void postSend(Message message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();

        switch ((accessor.getCommand())) {
            case CONNECT:
                LOGGER.info("Received a new web socket connection {}", sessionId);
                break;
            case DISCONNECT:
                LOGGER.info("sessionId Disconnected {}", sessionId);
                break;
            default:
                break;
        }

    }
}
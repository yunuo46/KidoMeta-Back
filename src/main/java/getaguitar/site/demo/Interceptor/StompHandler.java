package getaguitar.site.demo.Interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class StompHandler implements ChannelInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger( StompHandler.class );
    private final ThreadLocal<String> sessionIdHolder = new ThreadLocal<>();

    @Override
    public void postSend(Message message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        String sessionId = accessor.getSessionId();

        switch ((accessor.getCommand())) {
            case CONNECT:
                LOGGER.info("Received a new web socket connection {}", sessionId);
                sessionIdHolder.set(sessionId);
                break;
            case DISCONNECT:
                LOGGER.info("sessionId Disconnected {}", sessionId);
                break;
            case null, default:
                break;
        }
    }
    public String getSessionId() {
        return sessionIdHolder.get();
    }
}
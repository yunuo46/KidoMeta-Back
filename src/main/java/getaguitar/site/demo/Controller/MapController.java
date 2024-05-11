package getaguitar.site.demo.Controller;

import getaguitar.site.demo.Dto.MoveUser.MoveUserDto;
import getaguitar.site.demo.Dto.RemoveUser.ResRemoveUserDto;
import getaguitar.site.demo.Dto.StopUser.ResStopUserDto;
import getaguitar.site.demo.Dto.StopUser.ReqStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;
import getaguitar.site.demo.Entity.UserEntity;
import getaguitar.site.demo.Service.MapService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;

@RestController
@RequiredArgsConstructor
@MessageMapping("/map")
public class MapController {

    private final MapService mapService;
    private String sessionId;
    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor headerAccesor = StompHeaderAccessor.wrap(event.getMessage());
        this.sessionId = headerAccesor.getSessionId();
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        messagingTemplate.convertAndSend("/topic/map/remove", mapService.removeUser(sessionId));
    }

    @MessageMapping("/move")
    @SendTo("/topic/map/move")
    public MoveUserDto moveUser(MoveUserDto position) {
        return mapService.moveUser(position);
    }

    @MessageMapping("/new")
    @SendTo("/topic/map/new")
    public UserEntity newUser(@Payload ReqNewUserDto newUser) {
        return mapService.createUser(sessionId, newUser);
    }

    @MessageMapping("/all")
    @SendTo("/topic/map/all")
    public List<UserEntity> allUser(){
        return mapService.getAllUser();
    }

    @MessageMapping("/stop")
    @SendTo("/topic/map/stop")
    public ResStopUserDto stopUser(ReqStopUserDto stopUser) {
        return mapService.stopUser(stopUser);
    }
}

package getaguitar.site.demo.Controller;

import getaguitar.site.demo.Dto.MoveUser.MoveUserDto;
import getaguitar.site.demo.Dto.StopUser.ResStopUserDto;
import getaguitar.site.demo.Dto.StopUser.ReqStopUserDto;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;
import getaguitar.site.demo.Dto.NewUser.ResNewUserDto;
import getaguitar.site.demo.Entity.UserEntity;
import getaguitar.site.demo.Service.MapService;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@MessageMapping("/map")
public class MapController {

    private final MapService mapService;

    @MessageMapping("/move")
    @SendTo("/topic/map/move")
    public MoveUserDto moveUser(MoveUserDto position) {
        MoveUserDto moveUserDto = mapService.moveUser(position);
        return moveUserDto;
    }

    @MessageMapping("/new")
    @SendTo("/topic/map/new")
    public ResNewUserDto newUser(@Payload ReqNewUserDto newUser) {
        ResNewUserDto resNewUserDto = mapService.createUser(newUser);
        return resNewUserDto;
    }

    @MessageMapping("/all")
    @SendTo("/topic/map/all")
    public List<UserEntity> allUser(){
        return mapService.getAllUser();
    }

    @MessageMapping("/stop")
    @SendTo("/topic/map/stop")
    public ResStopUserDto stopUser(ReqStopUserDto stopUser) {
        ResStopUserDto resStopUserDto= mapService.stopUser(stopUser);
        return resStopUserDto;
    }
}

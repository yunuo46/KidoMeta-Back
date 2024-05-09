package getaguitar.site.demo.Controller;

import getaguitar.site.demo.Dto.Player;
import getaguitar.site.demo.Dto.NewUser.ReqNewUserDto;
import getaguitar.site.demo.Dto.NewUser.ResNewUserDto;
import getaguitar.site.demo.Service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@MessageMapping("/map")
public class MapController {

    private final MapService mapService;

    @MessageMapping("/move")
    @SendTo("/topic/map/move")
    public void moveUser() {
        //mapService.move();
    }

    @MessageMapping("/new")
    @SendTo("/topic/map/new")
    public ResNewUserDto newUser(@Payload ReqNewUserDto newUser) {
        ResNewUserDto resNewUserDto = mapService.createUser(newUser);
        return resNewUserDto;
    }

    @MessageMapping("/all")
    @SendTo("/topic/map/all")
    public Player[] allUser(){
        Player[] players = new Player[1];
        players[0] = new Player("TestId", 0, 0, "down");
        return players;
    }

    @MessageMapping("/stop")
    @SendTo("/topic/map/stop")
    public void stopUser() {
    }
}

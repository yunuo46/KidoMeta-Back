package getaguitar.site.demo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MessageMapping("/map")
@RequiredArgsConstructor
public class MapController {

    @MessageMapping("/move")
    @SendTo("/topic/map/move")
    public void moveUser() {
        //mapService.move();
    }

    @MessageMapping("/new")
    @SendTo("/topic/map/new")
    public void newUser() {
    }

    @MessageMapping("/stop")
    @SendTo("/topic/map/stop")
    public void stopUser() {
    }
}

package getaguitar.site.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemoryUserEntity {
    private Integer id;

    @Column(length = 45)
    private String username;

    @Column(length = 45)
    private String sessionId;

    private Integer x;

    private Integer y;

    private String direction;
}
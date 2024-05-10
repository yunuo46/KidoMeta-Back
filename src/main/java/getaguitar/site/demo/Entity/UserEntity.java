package getaguitar.site.demo.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", indexes = @Index(columnList = "username"))
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45)
    private String username;

    @NotNull
    private Integer x;

    @NotNull
    private Integer y;

    private String direction;
}
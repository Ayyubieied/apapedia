package apapedia.user.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "user_apapedia")
public class User {
    @Id
    private UUID idUser = UUID.randomUUID();

    @NotNull
    @Column(name = "name_user")
    private String nameUser;

    @NotNull
    @Column
    private String username;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column
    private LocalDateTime createdAt;

    @NotNull
    @Column
    private LocalDateTime updatedAt;
}

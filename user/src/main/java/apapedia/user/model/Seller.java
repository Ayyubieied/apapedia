package apapedia.user.model;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Value;

=======
>>>>>>> origin/development
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "seller")
public class Seller extends User{
    @NotNull
    @Column(nullable = false)
<<<<<<< HEAD
    private String category = "Biasa";
=======
    private String category;
>>>>>>> origin/development
}

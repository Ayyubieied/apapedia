package apapedia.order.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cart")
public class Cart {
    @Id
    private UUID id = UUID.randomUUID();
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;
    // @OneToOne(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private User userId;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @OneToMany(mappedBy = "cartId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItem = new ArrayList<>();
}

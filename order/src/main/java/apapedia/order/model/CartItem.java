package apapedia.order.model;

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
@Table(name = "cart_item")
public class CartItem {
    @Id
    private UUID id = UUID.randomUUID();
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;
    // @OneToOne(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Catalog productId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cartId;

    @Column(name = "quatity", nullable = false)
    private Integer quantity;
}

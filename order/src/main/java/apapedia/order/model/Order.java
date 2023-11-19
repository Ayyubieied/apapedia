package apapedia.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
// import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order")
public class Order {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "customer", nullable = false)
    private UUID customer;
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id_customer", referencedColumnName = "id")
    // private Customer customer;

    @Column(name = "seller", nullable = false)
    private UUID seller;
    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "id_seller", referencedColumnName = "id")
    // private Seller seller;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> orderItem = new ArrayList<>();
}

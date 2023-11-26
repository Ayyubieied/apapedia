package apapedia.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apapedia.order.model.CartItem;

@Repository
public interface CartItemDb extends JpaRepository<CartItem, UUID> {
    CartItem findCartItemByItemId(UUID itemId);
}

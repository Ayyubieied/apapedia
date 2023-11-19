package apapedia.order.service;

import java.util.List;
import java.util.UUID;

import apapedia.order.model.Cart;

public interface CartService {
    

    List<Cart> getAllCart();

    // Cart updateCart(Cart cart);

    void saveCart(Cart cart);

    // Cart getCartById(UUID id);
}

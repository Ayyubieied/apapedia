package apapedia.order.service;

import apapedia.order.repository.CartDb;
import apapedia.order.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDb cartDb;

    @Override
    public void saveCart(Cart cart) {
        cartDb.save(cart);
    }

    @Override
    public List<Cart> getAllCart() {
        return cartDb.findAll();
    }

    @Override
    public Cart getCartById(UUID id) {
        for (Cart cart : getAllCart()) {
            if (cart.getCartId() == id) {
                return cart;
            }
        }
        return null;
    }

    @Override
    public Cart updateCart(Cart cartFromDTO) {
        Cart cart = getCartById(cartFromDTO.getCartId());
        if (cart != null){
            cart.setListCartItem(cartFromDTO.getListCartItem());
            cartDb.save(cart);
        }
        return cart;
    }
}

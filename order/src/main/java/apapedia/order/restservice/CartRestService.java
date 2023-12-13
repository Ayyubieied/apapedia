package apapedia.order.restservice;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apapedia.order.model.*;
import apapedia.order.repository.*;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartRestService {
    @Autowired
    private CartDb cartDb;

    @Autowired
    private CartItemDb cartItemDb;

    public void createRestCart(Cart cart){
        cartDb.save(cart);
    }

    public Cart getCartById(UUID cartId){
        return cartDb.findCartByCartId(cartId);
    }

    public Cart getCartByUser(UUID userId){
        return cartDb.findCartByUserId(userId);
    }

    public CartItem getCartItem(UUID itemId){
        return cartItemDb.findCartItemByItemId(itemId);
    }

    public List<Cart> getAllCart() {
        return cartDb.findAll();
    }

    public Cart updateRestCart(Cart cartDTO) {
        Cart cart = getCartById(cartDTO.getCartId());
        if (cart != null){
            cart.setListCartItem(cartDTO.getListCartItem());
            cartDb.save(cart);
        }
        return cart;
    }

    public CartItem updateRestCartItem(CartItem cartItemDTO) {
        CartItem cartItem = getCartItem(cartItemDTO.getItemId());
        if (cartItem != null){
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItemDb.save(cartItem);
        }
        return cartItem;
    }

    public void deleteRestCartItem(CartItem cartItem){
        cartItem.setIsDeleted(true);
        cartItemDb.save(cartItem);
    }

}
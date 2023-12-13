package apapedia.order.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apapedia.order.dto.CartMapper;
import apapedia.order.dto.request.CreateCartItemRequestDTO;
import apapedia.order.dto.request.CreateCartRequestDTO;
import apapedia.order.dto.request.UpdateCartItemRequestDTO;
import apapedia.order.dto.request.UpdateCartRequestDTO;
import apapedia.order.dto.response.CartResponseDTO;
import apapedia.order.model.CartItem;
import apapedia.order.restservice.CartRestService;
import jakarta.validation.Valid;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    @Autowired
    CartRestService cartService;

    @Autowired
    CartMapper cartMapper;

    @PostMapping("/create")
    public ResponseEntity<CartResponseDTO> createCart(@RequestBody CreateCartRequestDTO cartDTO){
        var cart = cartMapper.createCartRequestDTOToCart(cartDTO);
        cartService.createRestCart(cart);
        var cartResponse = cartMapper.cartToCartResponseDTO(cart);
        return ResponseEntity.ok(cartResponse);
    }

    @PostMapping("/add-item/{cartId}")
    public ResponseEntity<CartResponseDTO> addCartItem(@PathVariable("cartId") UUID cartId, @RequestBody CreateCartItemRequestDTO cartItemDTO){
        var cartItem = cartMapper.createCartItemRequestDTOToCartItem(cartItemDTO);
        var cart = cartService.getCartById(cartId);
        cartItem.setCart(cart);
        if (cart.getListCartItem() == null || cart.getListCartItem().size() == 0) { 
            cart.setListCartItem(new ArrayList<>());
        }
        cart.getListCartItem().add(cartItem); 
        cartService.updateRestCart(cart);
        var cartResponse = cartMapper.cartToCartResponseDTO(cart);
        return ResponseEntity.ok(cartResponse);
    }

    @RequestMapping(value="/edit/{itemId}", method = RequestMethod.PUT)
    public ResponseEntity<CartResponseDTO> updateCartItem(@PathVariable("itemId") UUID itemId, @Valid @RequestBody UpdateCartItemRequestDTO cartItemDTO, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            cartItemDTO.setItemId(itemId);
            var cartItem = cartMapper.updateCartItemRequestDTOToCartItem(cartItemDTO);
            var cartItemFromDto = cartService.getCartItem(itemId);
            cartItem.setProductId(cartItemFromDto.getProductId());
            cartItem.setCart(cartItemFromDto.getCart());
            cartItem.setIsDeleted(cartItemFromDto.getIsDeleted());
            cartService.updateRestCartItem(cartItem);
            var cart = cartService.getCartById(cartItem.getCart().getCartId());
            var cartResponse = cartMapper.cartToCartResponseDTO(cart);
            return ResponseEntity.ok(cartResponse);
        }
    }

    @GetMapping("/items/{userId}")
    public ResponseEntity<CartResponseDTO> retrieveCartItem(@PathVariable("userId") UUID userId){
        var cart = cartService.getCartByUser(userId);
        var cartResponse = cartMapper.cartToCartResponseDTO(cart);
        return ResponseEntity.ok(cartResponse);
    }

    @RequestMapping(value="/delete/{itemId}", method = RequestMethod.DELETE)
    public String deleteCartItem(@PathVariable("itemId") UUID itemId) {
        var cartItem = cartService.getCartItem(itemId);
        cartService.deleteRestCartItem(cartItem);
        return "Cart item has been deleted";
    }
}

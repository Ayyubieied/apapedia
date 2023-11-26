package apapedia.order.DTO;

import apapedia.order.DTO.request.CreateCartRequestDTO;
import apapedia.order.DTO.request.CreateCartItemRequestDTO;
import apapedia.order.DTO.request.UpdateCartRequestDTO;
import apapedia.order.DTO.request.UpdateCartItemRequestDTO;
import apapedia.order.DTO.response.CartResponseDTO;
import apapedia.order.model.Cart;
import apapedia.order.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart createCartRequestDTOToCart(CreateCartRequestDTO createCartRequestDTO);
    CartResponseDTO cartToCartResponseDTO(Cart cart);
    CartItem createCartItemRequestDTOToCartItem(CreateCartItemRequestDTO createCartItemRequestDTO);
    CartItem updateCartItemRequestDTOToCartItem(UpdateCartItemRequestDTO updateCartItemRequestDTO);
    Cart updateCartRequestDTOToCart(UpdateCartRequestDTO updateCartRequestDTO);
}
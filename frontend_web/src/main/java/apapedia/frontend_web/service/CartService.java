package apapedia.frontend_web.service;

import apapedia.frontend_web.dto.request.CreateCartRequestDTO;
import apapedia.frontend_web.dto.request.CreateCartItemRequestDTO;
import apapedia.frontend_web.dto.response.CartDTO;

import java.util.List;
import java.util.UUID;

public interface CartService {
    // List<SimplifiedKategoriDTO> getAllKategori();
    void addCart(CreateCartRequestDTO cartDTO);
    void addCartItem(UUID cartId, CreateCartItemRequestDTO cartItemDTO);
    CartDTO getCartByUser(UUID userId);
    // List<CartDTO> getAllCatalogBySeller(UUID id);
    // ReadCatalogDTO deleteProduk(UUID id);
    // List<ReadCatalogDTO> getAllCatalog();
}

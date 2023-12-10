package apapedia.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import apapedia.order.service.CartService;
import apapedia.order.dto.CartMapper;
import apapedia.order.dto.request.CreateCartRequestDTO;
import apapedia.order.dto.request.UpdateCartRequestDTO;
import apapedia.order.model.Cart;
import apapedia.order.model.CartItem;

@Controller
public class CartController {
    // @Autowired
    // private CartMapper cartMapper;

    // @Autowired
    // private CartService cartService;

    @GetMapping("/")
    public String test() {
        return "profile-page";
    }

    // @GetMapping("cart/{cartId}/add-item")
    // public String formAddItem(@PathVariable("cartId") UUID cartId, Model model) {
    //     var cartDTO = new CreateCartRequestDTO();
    //     model.addAttribute("cartDTO", cartDTO);

    //     var cart = cartService.getCartById(cartId);
    //     model.addAttribute("cart", cart);
        
    //     var listCatalogExisting = catalogService.getAllCatalog();
    //     model.addAttribute("listCatalogExisting", listCatalogExisting);
    //     return "form-add-item";
    // }

    // @PostMapping("cart/{cartId}/add-item")
    // public String addItem(@PathVariable("cartId") UUID cartId, @ModelAttribute UpdateCartRequestDTO cartDTO, Model model) {
    //     var cart = cartMapper.updateCartRequestDTOToCart(cartDTO);
    //     for (CartItem cartItem : cartDTO.getListCartItem()) {
    //         cartItem.setCart(cart);
    //     }
    //     cartService.updateCart(cart);
    //     model.addAttribute("cartId", cart.getCartId());
    //     return "success-add-item";
    // }

    // @PostMapping(value = "cart/{cartId}/add-item", params = {"addRow"})
    // public String addRowCartItem(@PathVariable("cartId") UUID cartId, @ModelAttribute CreateCartRequestDTO createCartRequestDTO, Model model) {
    //     var cart = cartService.getCartById(cartId);
    //     if (createCartRequestDTO.getListCartItem() == null || createCartRequestDTO.getListCartItem().size() == 0) { 
    //         createCartRequestDTO.setListCartItem(new ArrayList<>());
    //     }
    //     createCartRequestDTO.getListCartItem().add(new CartItem());
    //     model.addAttribute("cartDTO", createCartRequestDTO);
    //     model.addAttribute("cart", cart);
    //     model.addAttribute("cartId", cart.getCartId());

    //     var listCatalogExisting = catalogService.getAllCatalog();
    //     model.addAttribute("listCatalogExisting", listCatalogExisting);

    //     return "form-add-item";
    // } 

    // @GetMapping("cart-item/{itemId}/edit")
    // public String formEditItem(@PathVariable("itemId") UUID itemId, Model model) {
    //     var cartItem = cartItemService.getItembyId(itemId);
    //     var cartItemDTO = cartItemMapper.barangToUpdateBarangRequestDTO(barang);
    //     model.addAttribute("barangDTO", barangDTO);
    //     return "form-ubah-barang";
    // }

    // @PostMapping("barang/ubah")
    // public String ubahBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, Model model) {
    //     var barangFromDTO = barangMapper.updateBarangRequestDTOToBarang(barangDTO);
    //     var barang = barangService.ubahBarang(barangFromDTO);
    //     model.addAttribute("skuBarang", barang.getSkuBarang());
    //     return "success-ubah-barang";
    // }
}

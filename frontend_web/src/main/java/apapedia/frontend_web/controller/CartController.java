package apapedia.frontend_web.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import apapedia.frontend_web.dto.request.CreateCartRequestDTO;
import apapedia.frontend_web.dto.request.CreateCartItemRequestDTO;
import apapedia.frontend_web.service.CartService;


@Controller
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("cart/create")
    public String formAddCart(Model model){
        var cartDTO = new CreateCartRequestDTO();
        model.addAttribute("cartDTO", cartDTO);
        return "form-add-cart";
    }

    @PostMapping("cart/create")
    public String addCart(@ModelAttribute CreateCartRequestDTO cartDTO, Model model){
        cartService.addCart(cartDTO);
        return "success-add-cart";
    }

    @GetMapping("cart/add-item/{cartId}")
    public String formAddCartItem(@PathVariable("cartId") UUID cartId, Model model){
        var cartItemDTO = new CreateCartItemRequestDTO();
        model.addAttribute("cartItemDTO", cartItemDTO);
        model.addAttribute("cartId", cartId);
        return "form-add-cart-item";
    }

    @PostMapping("cart/add-item/{cartId}")
    public String addCartItem(@PathVariable("cartId") UUID cartId, @ModelAttribute CreateCartItemRequestDTO cartItemDTO, Model model){
        cartService.addCartItem(cartId, cartItemDTO);
        return "success-add-cart-item";
    }
}

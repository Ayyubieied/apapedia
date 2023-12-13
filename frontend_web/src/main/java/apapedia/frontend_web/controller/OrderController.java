package apapedia.frontend_web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import apapedia.frontend_web.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/order/seller/{userId}")
    public String listSellerOrder(@PathVariable("userId") UUID userId, Model model){
        var listOrder = orderService.getSellerOrder(userId);

        model.addAttribute("listOrder", listOrder);

        model.addAttribute("userId", userId);
        
        return "order-history-page";
    }

    @GetMapping("/order/{orderId}")
    public String updateOrder(@PathVariable("orderId") UUID orderId, Model model){
        var response = orderService.updateOrder(orderId);

        return "redirect:/order/seller/" + response.getSeller();
    }
}

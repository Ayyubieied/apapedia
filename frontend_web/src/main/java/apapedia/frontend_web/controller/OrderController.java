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

    @GetMapping("order/seller/update/{orderId}")
    public String updateSellerOrder(@PathVariable("orderId") UUID orderId, Integer status, Model model){
        var listOrder = orderService.updateOrder(orderId, status); 
        return "";
    }
    
    // @PutMapping("/api/order/{orderId}")
    // public String updateOrder(@RequestBody UpdateOrderDto updateOrderDto, @PathVariable("orderId") UUID orderId){
    //     var order = orderService.getOrderById(orderId);
    //     return "";
    // }


}

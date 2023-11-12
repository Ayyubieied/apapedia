package apapedia.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {
    @GetMapping("/Order")
    public String createOrder(Model model) {
        return "profile-page";
    }
}

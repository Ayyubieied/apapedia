package apapedia.order.controller;

import apapedia.order.dto.CreateOrderDto;
import apapedia.order.dto.StatsDto;
import apapedia.order.dto.UpdateOrderDto;
import apapedia.order.model.Order;
import apapedia.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/{userId}")
    public ResponseEntity<String> addOrder(@RequestBody List<CreateOrderDto> createOrderDto, @PathVariable("userId") UUID userId) {
        orderService.createOrder(createOrderDto, userId);
        return ResponseEntity.ok("Order successfully created!");
    }

    @GetMapping("/customer/{userId}")
    public ResponseEntity<List<Order>> getOrderCustomer(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(orderService.getCustomerOrder(userId));
    }

    @GetMapping("/seller/{userId}")
    public ResponseEntity<List<Order>> getOrderSeller(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(orderService.getSellerOrder(userId));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> updateOrderStatus(@RequestBody UpdateOrderDto updateOrderDto, @PathVariable("orderId") UUID orderId) {
        orderService.updateOrder(orderId, updateOrderDto);
        return ResponseEntity.ok("Status successfully updated");
    }

    @GetMapping("/seller/{userId}/stats")
    public ResponseEntity<List<StatsDto>> getStats(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(orderService.getStats(userId));
    }
}

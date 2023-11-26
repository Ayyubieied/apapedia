package apapedia.order.service;

import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import apapedia.order.model.Cart;
import apapedia.order.model.CartItem;
import apapedia.order.model.Order;
import apapedia.order.model.OrderItem;
import apapedia.order.repository.OrderDb;
import apapedia.order.repository.OrderItemDb;

public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDb orderDb;

    @Autowired
    OrderItemDb orderItemDb;

    @Override
    public Order cartOrder(Cart cart) {
        List<CartItem> cartItemList = cart.getCartItem();
        Order order = new Order();

        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        order.setStatus(0);
        order.setTotalPrice(cart.getTotalPrice());
        order.setCustomer(cart.getUserId());
        order.setSeller(UUID.randomUUID());
        orderDb.save(order);

        for (CartItem cartItem : cartItemList) {
            OrderItem orderItem = new OrderItem();

            orderItem.setProductId(cartItem.getProductId());
            orderItem.setOrderId(order.getId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProductName(1);
            orderItem.setProductPrice(10000);
            // order.getOrderItem().add(cartItem);
            orderItemDb.save(orderItem);
        }
        orderDb.save(order);

        return order;
    }

    @Override
    public Order createOrder(CartItem cartItem, UUID userId) {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();

        orderItem.setProductId(cartItem.getProductId());
        orderItem.setOrderId(order.getId());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setProductName(1);
        orderItem.setProductPrice(10000);

        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        order.setStatus(0);
        order.setTotalPrice(cartItem.getQuantity() * orderItem.getProductPrice());
        order.setCustomer(userId);
        order.setSeller(UUID.randomUUID());

        // order.getOrderItem().add(cartItem);
        orderItemDb.save(orderItem);
        orderDb.save(order);

        return order;
    }

    public Order updateOrder(Order order, Integer status) {
        order.setStatus(status);
        orderDb.save(order);

        return order;
    }
    
}

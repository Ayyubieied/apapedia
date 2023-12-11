package apapedia.order.restservice;

import java.util.*;
import java.time.LocalDate;

import apapedia.order.DTO.request.CreateOrderDto;
import apapedia.order.DTO.request.StatsDto;
import apapedia.order.DTO.request.UpdateOrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import apapedia.order.model.Cart;
import apapedia.order.model.CartItem;
import apapedia.order.model.Order;
import apapedia.order.model.OrderItem;
import apapedia.order.repository.OrderDb;
import apapedia.order.repository.OrderItemDb;
import org.springframework.stereotype.Service;

@Service
public class OrderRestServiceImpl implements OrderRestService {

    @Autowired
    OrderDb orderDb;

    @Autowired
    OrderItemDb orderItemDb;

    @Override
    public void createOrder(List<CreateOrderDto> createOrderDto, UUID userId) {
        Map<UUID, Order> sellerOrders = new HashMap<>();

        for (CreateOrderDto orderItem : createOrderDto) {
            OrderItem item = new OrderItem();
            item.setQuantity(orderItem.getQuantity());
            item.setProductId(orderItem.getProductId());
            item.setProductName(orderItem.getProductName());
            item.setProductPrice(orderItem.getPrice());

            UUID sellerId = orderItem.getSeller();

            Order sellerOrder = sellerOrders.getOrDefault(sellerId, new Order());
            sellerOrder.setSeller(sellerId);
            sellerOrder.setCustomer(userId);
            sellerOrder.setTotalPrice(sellerOrder.getTotalPrice() + (item.getProductPrice() * item.getQuantity()));

            Order createdOrder = orderDb.save(sellerOrder);
            item.setOrderId(createdOrder);
            orderItemDb.save(item);

            sellerOrders.put(sellerId, createdOrder);
        }
    }

    @Override
    public void updateOrder(UUID orderId, UpdateOrderDto updateOrderDto) {
        Order order = orderDb.findById(orderId).get();
        order.setStatus(updateOrderDto.getStatus());
        orderDb.save(order);
    }

    @Override
    public List<Order> getCustomerOrder(UUID userId) {
        return orderDb.findAllByCustomer(userId);
    }

    @Override
    public List<Order> getSellerOrder(UUID userId) {
        return orderDb.findAllBySeller(userId);
    }

    @Override
    public List<StatsDto> getStats(UUID userId) {
        List<StatsDto> stats = new ArrayList<>();
        Map<String, Integer> statsMap = new HashMap<>();
        List<Order> orders = orderDb.findAllBySeller(userId);
        for (Order order:  orders) {
            if (order.getStatus() == 5) {
                for (OrderItem orderItem: order.getOrderItem()                    ) {
                    if (statsMap.containsKey(orderItem.getProductName())) {
                        statsMap.put(orderItem.getProductName(), statsMap.get(orderItem.getProductName()) + 1);
                    } else {
                        statsMap.put(orderItem.getProductName(), 1);
                    }
                }
            }
        }

        for (Map.Entry<String, Integer> entry : statsMap.entrySet()) {
            stats.add(new StatsDto(entry.getKey(), entry.getValue()));
        }

        return stats;
    }

}

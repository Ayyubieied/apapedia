package apapedia.order.restservice;

import java.util.List;
import java.util.UUID;

import apapedia.order.DTO.request.CreateOrderDto;
import apapedia.order.DTO.request.StatsDto;
import apapedia.order.DTO.request.UpdateOrderDto;
import apapedia.order.model.Order;
import apapedia.order.model.OrderItem;

public interface OrderRestService {

    void createOrder(List<CreateOrderDto> createOrderDto, UUID userId);

    public Order updateOrder(UUID orderId);

    List<Order> getCustomerOrder(UUID userId);

    List<Order> getSellerOrder(UUID userId);

    List<OrderItem> findByOrder(Order OrderId);

    List<StatsDto> getStats(UUID userId);
}

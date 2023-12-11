package apapedia.order.restservice;

import java.util.List;
import java.util.UUID;

import apapedia.order.dto.request.CreateOrderDto;
import apapedia.order.dto.request.StatsDto;
import apapedia.order.dto.request.UpdateOrderDto;
import apapedia.order.model.Cart;
import apapedia.order.model.CartItem;
import apapedia.order.model.Order;

public interface OrderRestService {

    void createOrder(List<CreateOrderDto> createOrderDto, UUID userId);

    void updateOrder(UUID orderId, UpdateOrderDto updateOrderDto);

    List<Order> getCustomerOrder(UUID userId);
    
    List<Order> getSellerOrder(UUID userId);

    List<StatsDto> getStats(UUID userId);
}

package apapedia.order.DTO;

import apapedia.order.DTO.request.CreateOrderDto;
import apapedia.order.DTO.request.UpdateOrderDto;
import apapedia.order.DTO.response.OrderResponseDTO;
import apapedia.order.model.Order;

public interface OrderMapper {
    OrderResponseDTO orderToOrderResponseDTO(Order order);

    Order createOrderDTOToOrder(CreateOrderDto createOrderDto);

    Order updateOrderDTOToOrder(UpdateOrderDto updateOrderDto);
}

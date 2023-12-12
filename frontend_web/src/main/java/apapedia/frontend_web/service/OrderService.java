package apapedia.frontend_web.service;

import apapedia.frontend_web.dto.response.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    public List<OrderDTO> getSellerOrder(UUID userId);

    public String updateOrder(UUID orderId, Integer status);
}

package apapedia.frontend_web.service;

import apapedia.frontend_web.dto.response.OrderDTO;
import apapedia.frontend_web.dto.response.StatsDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    public List<OrderDTO> getSellerOrder(UUID userId);

    public String updateOrder(UUID orderId, Integer status);

    public List<StatsDTO> getStats(String userId);
}

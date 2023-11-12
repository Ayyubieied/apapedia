package apapedia.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import apapedia.order.model.OrderItem;

public interface OrderItemDb extends JpaRepository<OrderItem, UUID> {
    List<OrderItem> findAll();

    List<OrderItem> findByProductName(Integer productName);
}

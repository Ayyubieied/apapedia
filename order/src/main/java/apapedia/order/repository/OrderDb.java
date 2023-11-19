package apapedia.order.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import apapedia.order.model.Order;

public interface OrderDb extends JpaRepository<Order, UUID> {
    List<Order> findAll();
    
    //Order findById(UUID id);
}

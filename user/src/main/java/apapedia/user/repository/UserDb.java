package apapedia.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apapedia.user.model.User;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserDb extends JpaRepository<User,UUID>{
    
}

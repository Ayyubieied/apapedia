package apapedia.user.repository;

<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> origin/development
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apapedia.user.model.User;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserDb extends JpaRepository<User,UUID>{
<<<<<<< HEAD
    
=======
    Optional<User> findByUsername(String username);
    User findByIdUser(UUID uuid);
>>>>>>> origin/development
}

package apapedia.catalog.repository;

import apapedia.catalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CategoryDb extends JpaRepository<Category, UUID> {
    Category findByIdCategory(UUID idCategory);
}

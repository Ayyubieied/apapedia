package apapedia.catalog.repository;

import apapedia.catalog.model.Catalog;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CatalogDb extends JpaRepository<Catalog, UUID> {

    List<Catalog> findAll();

    @Query("SELECT b FROM Catalog b ORDER BY b.productName")
    List<Catalog> sortCatalogByProductName();

    List<Catalog> findAllBySellerId(UUID id);

}

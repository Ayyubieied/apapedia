package apapedia.catalog.repository;

import apapedia.catalog.model.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

@Repository
public interface CatalogDb extends JpaRepository<Catalog, UUID> {
    List<Catalog> findAllByOrderByProductNameAsc();

    List<Catalog> findAllByOrderByProductNameDesc();

    List<Catalog> findAllByOrderByPriceAsc();

    List<Catalog> findAllByOrderByPriceDesc();

    List<Catalog> findAll();

    @Query("SELECT b FROM Catalog b ORDER BY b.productName")
    List<Catalog> sortCatalogByProductName();

    List<Catalog> findAllBySellerId(UUID id);
}

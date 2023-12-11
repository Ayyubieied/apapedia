package apapedia.catalog.restservice;

import apapedia.catalog.model.Catalog;

import java.util.List;
import java.util.UUID;

public interface CatalogRestService {
    Catalog createCatalog(Catalog catalog);

    Catalog updateCatalog(Catalog catalog);

    Catalog getCatalogById(UUID id);

    List<Catalog> getAll();

    void deleteCatalog(UUID id);

    List<Catalog> getSortedCatalog(String sortBy, String sortMethod);

    List<Catalog> retrieveRestAllCatalog();

    List<Catalog> retrieveRestAllCatalogBySellerId(UUID sellerId);
}


package apapedia.catalog.restservice;

import apapedia.catalog.model.Catalog;
import java.util.List;
import java.util.UUID;


public interface CatalogRestService {
    List<Catalog> retrieveRestAllCatalog();

    List<Catalog> retrieveRestAllCatalogBySellerId(UUID sellerId);

}

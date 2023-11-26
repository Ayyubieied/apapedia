package apapedia.catalog.restservice;

import apapedia.catalog.model.Catalog;
import apapedia.catalog.repository.CatalogDb;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import apapedia.catalog.rest.Setting;
import jakarta.transaction.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@Transactional
public class CatalogRestServiceImpl implements CatalogRestService{
    @Autowired
    CatalogDb catalogDb;

    private final WebClient webClient;

    public CatalogRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.mockUrl).build(); // mock server
    }

    @Override
    public List<Catalog> retrieveRestAllCatalog(){
        return catalogDb.sortCatalogByProductName();
    }

    @Override
    public List<Catalog> retrieveRestAllCatalogBySellerId(UUID sellerId) {
        return catalogDb.findAllBySellerId(sellerId);
    }

    @Override
    public Catalog createCatalog(Catalog catalog) {
        return catalogDb.save(catalog);
    }

    @Override
    public List<Catalog> getAll() {
        return catalogDb.findAllByOrderByProductNameAsc();
    }

    @Override
    public Catalog getCatalogById(UUID id) {
        var catalog = catalogDb.findById(id);
        if (catalog.isPresent() && !catalog.get().isDeleted()) return catalog.get();
        else throw new EntityNotFoundException("Catalog not found");
    }

    @Override
    public Catalog updateCatalog(Catalog catalog) {
        var oldCatalog = getCatalogById(catalog.getId());
        oldCatalog.setStock(catalog.getStock());
        oldCatalog.setImagePath(catalog.getImagePath());
        oldCatalog.setProductName(catalog.getProductName());
        oldCatalog.setProductDescription(catalog.getProductDescription());
        oldCatalog.setPrice(catalog.getPrice());
        oldCatalog.setCategory(catalog.getCategory());
        return catalogDb.save(oldCatalog);
    }

    @Override
    public void deleteCatalog(UUID id) {
        var catalog = getCatalogById(id);
        catalog.setDeleted(true);
        catalogDb.save(catalog);
    }

    @Override
    public List<Catalog> getSortedCatalog(String sortBy, String sortMethod) {
        if (sortBy.equals("name")) {
            return sortMethod.toLowerCase().equals("desc") ? catalogDb.findAllByOrderByProductNameDesc() : catalogDb.findAllByOrderByProductNameAsc();
        } else if (sortBy.equals("price")) {
            return sortMethod.toLowerCase().equals("desc") ? catalogDb.findAllByOrderByPriceDesc() : catalogDb.findAllByOrderByPriceAsc();
        } else {
            throw new RestClientException("Invalid attribute to sort");
        }
    }
}

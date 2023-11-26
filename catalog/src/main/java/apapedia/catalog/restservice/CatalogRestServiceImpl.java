package apapedia.catalog.restservice;

import apapedia.catalog.model.Catalog;
import apapedia.catalog.repository.CatalogDb;
import apapedia.catalog.rest.Setting;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CatalogRestServiceImpl implements CatalogRestService{

    @Autowired
    private CatalogDb catalogDb;

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
}

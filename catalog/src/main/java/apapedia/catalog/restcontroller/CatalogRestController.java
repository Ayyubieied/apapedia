package apapedia.catalog.restcontroller;

import apapedia.catalog.model.Catalog;
import apapedia.catalog.restservice.CatalogRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CatalogRestController {

    @Autowired
    private CatalogRestService catalogRestService;

    @GetMapping(value = "/catalog/view-all")
    private List<Catalog> retrieveAllCatalog(){
        return catalogRestService.retrieveRestAllCatalog();
    }

    @GetMapping(value = "/catalog/{sellerId}")
    private List<Catalog> retrieveAllCatalogBySellerId(@PathVariable("sellerId") String sellerId){
        try{
            return catalogRestService.retrieveRestAllCatalogBySellerId(UUID.fromString(sellerId));
        } catch (NoSuchElementException e){
            //HttpRequest
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Id Seller " + sellerId + " not found"
            );
        }
    }

}

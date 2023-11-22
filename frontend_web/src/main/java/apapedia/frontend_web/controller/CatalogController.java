package apapedia.frontend_web.controller;

import apapedia.frontend_web.dto.request.CreateCatalogRequestDTO;

import apapedia.frontend_web.dto.response.CatalogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Controller
public class CatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/catalog")
    public String viewCatalogPage(Model model){
        String url = "http://localhost:8084/api/catalog/view-all";
        List<CatalogDTO> listCatalog = restTemplate.getForObject(url, List.class);
        model.addAttribute("listCatalog", listCatalog);
        return "view-catalog";
    }

    @GetMapping("/catalog/{sellerId}")
    public String viewCatalogSellerPage(Model model, @PathVariable UUID sellerId){
        String url = "http://localhost:8084/api/catalog/" + sellerId.toString();
        List<CatalogDTO> listCatalog = restTemplate.getForObject(url, List.class);
        model.addAttribute("listCatalog", listCatalog);
        return "view-catalog";
    }

    @GetMapping("/catalog/detail")
    public String detailCatalog(){
        return "catalog-detail";
    }

    @GetMapping("/order/history")
    public String orderHistory(){
        return "order-history-page";
    }

    @GetMapping("/catalog/tambah")
    public String formTambahCatalog(Model model) {
        var catalogDTO = new CreateCatalogRequestDTO();

        model.addAttribute("catalogDTO", catalogDTO);
        return "form-tambah-catalog";
    }

}


package apapedia.frontend_web.controller;

import apapedia.frontend_web.dto.request.CreateCatalogRequestDTO;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class CatalogController {

    @GetMapping("/catalog")
    public String viewCatalogPage(){
        return "view-catalog";
    }

    @GetMapping("/catalog/detail")
    public String detailCatalog(){
        return "catalog-detail";
    }

    @GetMapping("/catalog/tambah")
    public String formTambahCatalog(Model model) {
        var catalogDTO = new CreateCatalogRequestDTO();

        model.addAttribute("catalogDTO", catalogDTO);
        return "form-tambah-catalog";
    }

}

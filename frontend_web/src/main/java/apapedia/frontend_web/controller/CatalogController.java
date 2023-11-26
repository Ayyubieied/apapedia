package apapedia.frontend_web.controller;

import apapedia.frontend_web.dto.request.CreateCatalogRequestDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;

import apapedia.frontend_web.dto.request.UpdateCatalogRequestDTO;
import apapedia.frontend_web.dto.response.ReadCatalogResponseDTO;
import apapedia.frontend_web.dto.response.CategoryDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
public class CatalogController {
    //TODO: change this
    private static final String URL_API_CATALOG = "http://localhost:8084";

    @GetMapping("/catalog")
    public String viewCatalogPage(Model model){
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<List<ReadCatalogResponseDTO>> catalogResponse = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/all",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (catalogResponse.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("catalogs", catalogResponse.getBody());
            } else {
                model.addAttribute("error", catalogResponse.getBody());
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "catalog/view-catalog";
    }
    
    @GetMapping("/catalog/tambah")
    public String formTambahCatalog(Model model) {
        var catalogDTO = new CreateCatalogRequestDTO();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<List<CategoryDTO>> categories = restTemplate.exchange(
                    URL_API_CATALOG + "/api/category/all",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (categories.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("categories", categories.getBody());
            } else {
                model.addAttribute("error", "Categories not found, try again later");
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        model.addAttribute("catalogDTO", catalogDTO);
        return "catalog/form-tambah-catalog";
    }

    @PostMapping("/catalog/tambah")
    public String tambahCatalog(@ModelAttribute CreateCatalogRequestDTO catalogDTO, RedirectAttributes redirectAttributes) {
        //TODO: ganti sama ID seller yang lagi log in
        catalogDTO.setSeller(UUID.randomUUID());

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateCatalogRequestDTO> requestEntity = new HttpEntity<>(catalogDTO, headers);

        try {
            ResponseEntity<ReadCatalogResponseDTO> newCatalog = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/create-catalog",
                    HttpMethod.POST,
                    requestEntity,
                    ReadCatalogResponseDTO.class
            );

            if (newCatalog.getStatusCode().is2xxSuccessful()) {
                redirectAttributes.addFlashAttribute("success", "Berhasil menambahkan catalog");
            } else {
                redirectAttributes.addFlashAttribute("error", newCatalog.getBody());
                return "redirect:/catalog/tambah";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/catalog/tambah";
        }

        return "redirect:/catalog";
    }

    @GetMapping("/catalog/detail/{id}")
    public String detailCatalog(@PathVariable(value = "id") UUID catalogId, Model model){
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ReadCatalogResponseDTO> catalogResponse = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/" + catalogId,
                    HttpMethod.GET,
                    null,
                    ReadCatalogResponseDTO.class
            );

            if (catalogResponse.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("catalog", catalogResponse.getBody());
            } else {
                model.addAttribute("error", catalogResponse.getBody());
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "catalog/catalog-detail";
    }

    @GetMapping("/catalog/update/{id}")
    public String formUpdateCatalog(@PathVariable(value = "id") UUID catalogId, Model model) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UpdateCatalogRequestDTO> catalogResponse = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/" + catalogId,
                    HttpMethod.GET,
                    null,
                    UpdateCatalogRequestDTO.class
            );

            if (catalogResponse.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("catalogDTO", catalogResponse.getBody());
            } else {
                model.addAttribute("error", catalogResponse.getBody());
            }

            ResponseEntity<List<CategoryDTO>> categories = restTemplate.exchange(
                    URL_API_CATALOG + "/api/category/all",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );

            if (categories.getStatusCode().is2xxSuccessful()) {
                model.addAttribute("categories", categories.getBody());
            } else {
                model.addAttribute("error", "Categories not found, try again later");
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "catalog/form-ubah-catalog";
    }

    @PostMapping("/catalog/update")
    public String updateCatalog(@ModelAttribute UpdateCatalogRequestDTO catalogDTO, RedirectAttributes redirectAttributes) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateCatalogRequestDTO> requestEntity = new HttpEntity(catalogDTO, headers);

        try {
            ResponseEntity<ReadCatalogResponseDTO> updatedCatalog = restTemplate.exchange(
                    URL_API_CATALOG + "/api/catalog/update-catalog",
                    HttpMethod.PUT,
                    requestEntity,
                    ReadCatalogResponseDTO.class
            );

            if (updatedCatalog.getStatusCode().is2xxSuccessful()) {
                redirectAttributes.addFlashAttribute("success", "Berhasil meng-update catalog");
            } else {
                redirectAttributes.addFlashAttribute("error", updatedCatalog.getBody());
                return "redirect:/catalog/update/" + catalogDTO.getId();
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/catalog/update/" + catalogDTO.getId();
        }

        return "redirect:/catalog/detail/" + catalogDTO.getId();
    }
}
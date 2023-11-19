package apapedia.frontend_web.controller;

import apapedia.frontend_web.dto.request.CreateCatalogRequestDTO;
import jakarta.validation.Valid;
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

public class CatalogController {

    @Autowired
    CatalogService catalogService;
    
    @Autowired
    CatalogMapper catalogMapper;

    @GetMapping("/catalog/tambah")
    public String formTambahCatalog(Model model) {
        var catalogDTO = new CreateCatalogRequestDTO();

        model.addAttribute("catalogDTO", catalogDTO);
        return "form-tambah-catalog";
    }

    /*
    @PostMapping("/catalog/tambah")
    public RedirectView tambahCatalog(@Valid @ModelAttribute CreateCatalogRequestDTO catalogDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.append(error.getDefaultMessage()).append("<br>");
            }

            redirectAttributes.addFlashAttribute("status", errors);
            return new RedirectView("/catalog/tambah");
        }

        var barang = catalogMapper.createCatalogRequestDTOToBarang(catalogDTO);

        try {
            catalogService.savecatalog(barang);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("status", "Merk sudah didaftarkan");
            return new RedirectView("/barang/tambah");
        }

        redirectAttributes.addFlashAttribute("status", "Berhasil menambahkan " + barang.getMerk());
        return new RedirectView("/barang");
    }
    */

}

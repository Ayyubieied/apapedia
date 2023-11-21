package main.java.apapedia.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

import main.java.apapedia.user.DTO.SellerMapper;
import main.java.apapedia.user.DTO.request.EditSellerRequestDTO;
import main.java.apapedia.user.model.Seller;
import main.java.apapedia.user.service.SellerService;
import java.util.UUID;

@Controller
public class SellerController {
    
    @Autowired
    private SellerMapper sellerMapper;

    @Autowired
    private SellerService sellerService;

    @GetMapping("seller/edit/{id}")
    public String formEditSeller(@PathVariable("id") UUID id, Model model) {
        var seller = sellerService.getSellerById(id);
        var sellerDTO = sellerMapper.sellerToEditSellerRequestDTO(seller);
        model.addAttribute("sellerDTO", sellerDTO);
        return "edit-seller-page";
    }

    @PostMapping("seller/edit")
    public String editSeller(@Valid @ModelAttribute EditSellerRequestDTO sellerDTO, Model model) {
        var sellerFromDTO = sellerMapper.editSellerRequestDTOToSeller(sellerDTO);
        var seller = sellerService.editSeller(sellerFromDTO);
        model.addAttribute("id", seller.getIdSeller());
        return "success-edit-seller";
    }
}

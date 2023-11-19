package apapedia.frontend_web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import apapedia.frontend_web.dto.request.CreateUserRequestDTO;
import apapedia.frontend_web.dto.response.SellerDTO;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @GetMapping("/register")
    public String formRegisterSeller(Model model){
        var userDTO = new CreateUserRequestDTO();
        model.addAttribute("userDTO", userDTO);
        return "auth/register";
    }

    @PostMapping("/register")
    public ResponseEntity<SellerDTO> createSeller(@Valid @ModelAttribute CreateUserRequestDTO userDTO, BindingResult bindingResult){
        System.out.println("Ini nama " + userDTO.getNameUser());
        String uri = "http://localhost:8082/api/seller/create";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SellerDTO> response = restTemplate.postForEntity(uri, userDTO, SellerDTO.class);
        return response;
    }

    @GetMapping("/login")
    public String formLoginSeller(Model model){
        var userDTO = new CreateUserRequestDTO();
        model.addAttribute("userDTO", userDTO);
        return "auth/login";
    }
}

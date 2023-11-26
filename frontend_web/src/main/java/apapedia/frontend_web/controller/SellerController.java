package apapedia.frontend_web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import apapedia.frontend_web.dto.request.CreateUserRequestDTO;
<<<<<<< HEAD
import apapedia.frontend_web.dto.response.SellerDTO;
import jakarta.validation.Valid;

=======
import apapedia.frontend_web.dto.request.WithdrawDTO;
import apapedia.frontend_web.dto.response.SellerDTO;
import jakarta.validation.Valid;

import java.util.UUID;

>>>>>>> origin/development
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
<<<<<<< HEAD
=======

    @GetMapping("/withdraw/{idUser}")
    public String withdrawBalanceForm(@PathVariable("idUser") String idUser, Model model){
        // String uri = "http://localhost:8082/api/retrieve/" + idUser;
        // RestTemplate restTemplate = new RestTemplate();
        // ResponseEntity<SellerDTO> response = restTemplate.getForEntity(uri, SellerDTO.class);
        // var seller = response.getBody();

        var withdrawDTO = new WithdrawDTO();
        withdrawDTO.setIdUser(UUID.fromString(idUser));
        withdrawDTO.setBalance(100000);
        model.addAttribute("withdrawDTO", withdrawDTO);
        return "withdraw-balance";
    }

    // @PostMapping("/withdraw/{idUser}")
    // public String withdrawBalance(@PathVariable("idUser") UUID idUser, @Valid @ModelAttribute WithdrawDTO withdrawReqDTO, Model model){
    //     String uri = "http://localhost:8082/api/seller/update-balance";
    //     RestTemplate restTemplate = new RestTemplate();
    //     ResponseEntity<WithdrawDTO> response = restTemplate.postForEntity(uri, withdrawReqDTO, WithdrawDTO.class);
    //     var withdrawDTO = response.getBody();

    //     model.addAttribute("withdrawDTO", withdrawDTO);
    //     return "withdraw-balance";
    // }
    @GetMapping("/edit")
    public String formEditSeller(Model model){
        var userDTO = new CreateUserRequestDTO();
        model.addAttribute("userDTO", userDTO);
        return "edit-seller-page";
    }

    @PostMapping("/edit")
    public String editSeller() {
        return "success-edit-seller";
    }
>>>>>>> origin/development
}

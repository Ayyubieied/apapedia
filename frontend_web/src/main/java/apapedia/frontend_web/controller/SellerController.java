package apapedia.frontend_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import apapedia.frontend_web.dto.auth.LoginRequest;
import apapedia.frontend_web.dto.auth.LoginResponse;
import apapedia.frontend_web.dto.request.CreateUserRequestDTO;
import apapedia.frontend_web.dto.request.UpdateUserRequestDTO;
import apapedia.frontend_web.dto.request.WithdrawDTO;
import apapedia.frontend_web.dto.response.SellerDTO;
import apapedia.frontend_web.service.JwtService;
import jakarta.validation.Valid;

import apapedia.frontend_web.dto.request.WithdrawDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.UUID;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    JwtService jwtService;

    private final WebClient webClient;

    public SellerController(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.build();
    }

    @GetMapping("/register")
    public String formRegisterSeller(Model model){
        var userDTO = new CreateUserRequestDTO();
        model.addAttribute("userDTO", userDTO);
        return "auth/register";
    }

    @PostMapping("/register")
    public String createSeller(@Valid @ModelAttribute CreateUserRequestDTO userDTO, HttpServletResponse response){
        String uri = "http://localhost:8082/api/authentication/create/seller";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LoginResponse> loginResponse = restTemplate.postForEntity(uri, userDTO, LoginResponse.class);

        response.addCookie(new Cookie("jwtToken", loginResponse.getBody().getJwtToken()));

        return "redirect:/seller/login";
    }

    @GetMapping("/login")
    public String formLoginSeller(Model model){
        var loginRequest = new LoginRequest();
        model.addAttribute("userDTO", loginRequest);
        return "auth/login";
    }

    @PostMapping("/login")
    public String loginSeller(LoginRequest loginRequest, Model model, HttpServletResponse response){
        String uri = "http://localhost:8082/api/authentication/login";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<LoginResponse> loginResponse = restTemplate.postForEntity(uri, loginRequest, LoginResponse.class);

        response.addCookie(new Cookie("jwtToken", loginResponse.getBody().getJwtToken()));
        
        return "redirect:/catalog";
    }

    @GetMapping("/profile")
    public String profileUser(
                            HttpSession session,
                            Model model){
        String jwtToken = (String) session.getAttribute("token");
        String sellerId = jwtService.getIdFromJwtToken(jwtToken);

        var response = this.webClient
                .get()
                .uri("http://localhost:8082/api/seller/retrieve/" + sellerId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .retrieve()
                .bodyToMono(SellerDTO.class);
        var user = response.block();
        System.out.println("Ini profile user dari db  " + user.getNameUser());
        model.addAttribute("user", user);
        model.addAttribute("idUser", sellerId);

        return "profile-page";
    }

    @GetMapping("/withdraw/{idUser}")
    public String withdrawBalanceForm(
                            @PathVariable("idUser") String idUser, 
                            HttpSession session,
                            Model model){
        String jwtToken = (String) session.getAttribute("token");
        String sellerId = jwtService.getIdFromJwtToken(jwtToken);

        var response = this.webClient
                .get()
                .uri("http://localhost:8082/api/seller/retrieve/" + idUser)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .retrieve()
                .bodyToMono(SellerDTO.class);
        var user = response.block();

        var withdrawDTO = new WithdrawDTO();
        withdrawDTO.setIdUser(user.getIdUser());
        withdrawDTO.setBalance(user.getBalance());

        model.addAttribute("withdrawDTO", withdrawDTO);

        return "withdraw-balance";
    }

    @PostMapping("/withdraw/{idUser}")
    public String withdrawBalance(
                        @PathVariable("idUser") UUID idUser, 
                        HttpSession session,
                        @Valid @ModelAttribute WithdrawDTO withdrawReqDTO, 
                        Model model){
        withdrawReqDTO.setWithdrawal(-(withdrawReqDTO.getWithdrawal()));
        String jwtToken = (String) session.getAttribute("token");
        System.out.println("Ini total withdrawalnya " + withdrawReqDTO.getWithdrawal());

        var response = this.webClient
                .put()
                .uri("http://localhost:8082/api/seller/update-balance")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .bodyValue(withdrawReqDTO)
                .retrieve()
                .bodyToMono(WithdrawDTO.class);
        var withdrawDTO = response.block();
        System.out.println("Ini hasilnya "+withdrawDTO.getWithdrawal());
        model.addAttribute("withdrawDTO", withdrawDTO);
        return "withdraw-balance";
    }

    @GetMapping("/edit/{idUser}")
    public String formEditSeller(
                            @PathVariable("idUser") String idUser, 
                            HttpSession session,
                            Model model){
        String jwtToken = (String) session.getAttribute("token");

        var response = this.webClient
                .get()
                .uri("http://localhost:8082/api/seller/retrieve/" + idUser)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .retrieve()
                .bodyToMono(SellerDTO.class);
        var user = response.block();

        var userDTO = new UpdateUserRequestDTO();
        userDTO.setIdUser(user.getIdUser());

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("idUser", idUser);

        return "edit-seller-page";
    }

    @PostMapping("/edit/{idUser}")
    public String editSeller(
                            @PathVariable("idUser") String idUser, 
                            HttpSession session,
                            @Valid @ModelAttribute UpdateUserRequestDTO userDTO,
                            Model model){
        String jwtToken = (String) session.getAttribute("token");

        var response = this.webClient
                .post()
                .uri("http://localhost:8082/api/seller/edit/" + idUser)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                .bodyValue(userDTO)
                .retrieve()
                .bodyToMono(SellerDTO.class);
        var user = response.block();

        model.addAttribute("userDTO", userDTO);
        model.addAttribute("idUser", idUser);
        return "success-edit-seller";
    }
}

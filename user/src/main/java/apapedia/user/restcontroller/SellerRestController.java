package apapedia.user.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import apapedia.user.dto.UserMapper;
import apapedia.user.dto.request.CreateUserRequestDTO;
import apapedia.user.dto.request.UpdateUserRequestDTO;
import apapedia.user.dto.response.CustomerResponseDTO;
import apapedia.user.dto.response.SellerResponseDTO;
import apapedia.user.model.Seller;
import apapedia.user.restservice.UserRestService;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/seller")
public class SellerRestController {
    
    @Autowired
    UserRestService userService;

    @Autowired
    UserMapper userMapper;

    @PostMapping("/create")
    public ResponseEntity<SellerResponseDTO> createSeller(@Valid @RequestBody CreateUserRequestDTO sellerDTO, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }

        var seller = userMapper.createUserRequestDTOToSeller(sellerDTO);
        userService.createRestSeller(seller);
        var sellerResponse = userMapper.sellerToSellerResponseDTO(seller);
        return ResponseEntity.ok(sellerResponse);
    }

    @GetMapping("/retrieve/{idUser}")
    public ResponseEntity<SellerResponseDTO> retrieveSeller(@PathVariable("idUser") UUID idSeller){
        var seller = userService.getSeller(idSeller);
        var sellerResponse = userMapper.sellerToSellerResponseDTO(seller);
        return ResponseEntity.ok(sellerResponse);
    }
    
    @RequestMapping(value="/edit/{idUser}", method = RequestMethod.PUT)
    public ResponseEntity<SellerResponseDTO> restUpdateSeller(@PathVariable("idUser") UUID idSeller, @Valid @RequestBody UpdateUserRequestDTO sellerDTO, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        } else {
            sellerDTO.setIdUser(idSeller);
            var seller = userMapper.updateUserRequestDTOToSeller(sellerDTO);
            seller.setUpdatedAt(LocalDateTime.now());
            userService.updateRestSeller(seller);
            var sellerResponse = userMapper.sellerToSellerResponseDTO(seller);
            return ResponseEntity.ok(sellerResponse);
        }
    }
}

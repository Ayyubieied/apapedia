package apapedia.user.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
// import org.springframework.security.access.prepost.PreAuthorize;
>>>>>>> origin/development
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.PutMapping;
>>>>>>> origin/development
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apapedia.user.dto.UserMapper;
<<<<<<< HEAD
import apapedia.user.dto.request.CreateUserRequestDTO;
import apapedia.user.dto.response.CustomerResponseDTO;
=======
import apapedia.user.dto.auth.RegisterRequest;
import apapedia.user.dto.request.UpdateBalanceRequest;
import apapedia.user.dto.response.CustomerResponse;
import apapedia.user.dto.response.UpdateBalanceResponse;
>>>>>>> origin/development
import apapedia.user.restservice.UserRestService;
import jakarta.validation.Valid;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
    @Autowired
    UserRestService userService;

    @Autowired
    UserMapper userMapper;

<<<<<<< HEAD
    @PostMapping("/create/{idUser}")
    public ResponseEntity<CustomerResponseDTO> createSeller(@Valid @RequestBody CreateUserRequestDTO customerDTO, BindingResult bindingResult){
=======
    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createSeller(@Valid @RequestBody RegisterRequest customerDTO, BindingResult bindingResult){
>>>>>>> origin/development
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }

        var customer = userMapper.createUserRequestDTOToCustomer(customerDTO);
        userService.createRestCustomer(customer);
        var customerResponse = userMapper.customerToCustomerResponseDTO(customer);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/retrieve/{idUser}")
<<<<<<< HEAD
    public ResponseEntity<CustomerResponseDTO> retrieveCustomer(@PathVariable("idUser") UUID idCustomer){
=======
    // @PreAuthorize("hasAuthority('ROLE_CUSTOMER')") 
    public ResponseEntity<CustomerResponse> retrieveCustomer(@PathVariable("idUser") UUID idCustomer){
>>>>>>> origin/development
        var customer = userService.getCustomer(idCustomer);
        var customerResponse = userMapper.customerToCustomerResponseDTO(customer);
        return ResponseEntity.ok(customerResponse);
    }
<<<<<<< HEAD
=======

    @PutMapping("/update-balance")
    public ResponseEntity<UpdateBalanceResponse> topup(
                            @Valid @RequestBody UpdateBalanceRequest topUpRequest,
                            BindingResult bindingResult){
    if(bindingResult.hasFieldErrors()){
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
    }
        UpdateBalanceResponse response = userService.updateBalance(topUpRequest);
        return ResponseEntity.ok(response);
    }
>>>>>>> origin/development
}

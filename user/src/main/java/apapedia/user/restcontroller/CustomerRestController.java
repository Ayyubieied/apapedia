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
import org.springframework.web.server.ResponseStatusException;

import apapedia.user.dto.UserMapper;
import apapedia.user.dto.request.CreateUserRequestDTO;
import apapedia.user.dto.response.CustomerResponseDTO;
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

    @PostMapping("/create/{idUser}")
    public ResponseEntity<CustomerResponseDTO> createSeller(@Valid @RequestBody CreateUserRequestDTO customerDTO, BindingResult bindingResult){
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
    public ResponseEntity<CustomerResponseDTO> retrieveCustomer(@PathVariable("idUser") UUID idCustomer){
        var customer = userService.getCustomer(idCustomer);
        var customerResponse = userMapper.customerToCustomerResponseDTO(customer);
        return ResponseEntity.ok(customerResponse);
    }
}

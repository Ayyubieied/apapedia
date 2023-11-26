// package apapedia.user.restcontroller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;

// import apapedia.user.dto.UserMapper;
// import apapedia.user.dto.auth.LoginRequest;
// import apapedia.user.dto.auth.RegisterRequest;
// import apapedia.user.dto.profile.CustomerResponse;
// import apapedia.user.dto.profile.SellerResponse;
// import apapedia.user.restservice.JwtService;
// import apapedia.user.restservice.UserRestService;
// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/authentication")
// public class AuthController {
//     @Autowired
//     JwtService jwtService;

//     @Autowired
//     AuthenticationManager authenticationManager;

//     @Autowired
//     UserRestService userService;

//     @Autowired
//     UserMapper userMapper;

//     @PostMapping("/create/seller")
//     public ResponseEntity<SellerResponse> createSeller(@Valid @RequestBody RegisterRequest sellerDTO, BindingResult bindingResult){
//         if(bindingResult.hasFieldErrors()){
//             throw new ResponseStatusException(
//                 HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
//         }

//         var seller = userMapper.createUserRequestDTOToSeller(sellerDTO);
//         userService.createRestSeller(seller);
//         var sellerResponse = userMapper.sellerToSellerResponseDTO(seller);
//         return ResponseEntity.ok(sellerResponse);
//     }

//     @PostMapping("/create/customer")
//     public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody RegisterRequest customerDTO, BindingResult bindingResult){
//         if(bindingResult.hasFieldErrors()){
//             throw new ResponseStatusException(
//                 HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
//         }

//         var customer = userMapper.createUserRequestDTOToCustomer(customerDTO);
//         userService.createRestCustomer(customer);
//         var customerResponse = userMapper.customerToCustomerResponseDTO(customer);
//         return ResponseEntity.ok(customerResponse);
//     }

//     @PostMapping("/generateToken") 
//     public String authenticateAndGetToken(@RequestBody LoginRequest authRequest) { 
//         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())); 
//         if (authentication.isAuthenticated()) { 
//             return jwtService.generateToken(authRequest.getUsername()); 
//         } else { 
//             throw new UsernameNotFoundException("invalid user request !"); 
//         } 
//     }
// }

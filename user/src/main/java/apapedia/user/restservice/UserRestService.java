package apapedia.user.restservice;

<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> origin/development
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.stereotype.Service;

import apapedia.user.model.Customer;
import apapedia.user.model.Seller;
import apapedia.user.repository.CustomerDb;
import apapedia.user.repository.SellerDb;
import jakarta.transaction.Transactional;
=======
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import apapedia.user.dto.request.UpdateBalanceRequest;
import apapedia.user.dto.response.UpdateBalanceResponse;
import apapedia.user.model.Customer;
import apapedia.user.model.Seller;
import apapedia.user.model.User;
import apapedia.user.repository.CustomerDb;
import apapedia.user.repository.SellerDb;
import apapedia.user.repository.UserDb;

import jakarta.transaction.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

>>>>>>> origin/development

@Service
@Transactional
public class UserRestService {
    @Autowired
    private SellerDb sellerDb;

    @Autowired
    private CustomerDb customerDb;

<<<<<<< HEAD
    public void createRestSeller(Seller seller){
        seller.setCreatedAt(LocalDateTime.now());
        seller.setUpdatedAt(LocalDateTime.now());
=======
    @Autowired
    private UserDb userDb;

    // @Autowired
    // private PasswordEncoder encoder;

    public void createRestSeller(Seller seller){
        // seller.setPassword(encoder.encode(seller.getPassword()));
        seller.setCreatedAt(LocalDateTime.now());
        seller.setUpdatedAt(LocalDateTime.now());
        seller.setRole("seller");
>>>>>>> origin/development
        sellerDb.save(seller);
    }

    public Seller getSeller(UUID idSeller){
        return sellerDb.findSellerByIdUser(idSeller);
    }

    public void createRestCustomer(Customer customer){
<<<<<<< HEAD
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
=======
        // customer.setPassword(encoder.encode(customer.getPassword()));
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setRole("customer");
>>>>>>> origin/development
        customerDb.save(customer);
    }

    public Customer getCustomer(UUID idSeller){
        return customerDb.findCustomerByIdUser(idSeller);
    }
<<<<<<< HEAD
=======

    public void deleteUser(Seller user){
        System.out.println("masuk delete 2");
        userDb.delete(user);
    }

    public UpdateBalanceResponse updateBalance(UpdateBalanceRequest request){
        User user = userDb.findByIdUser(request.getIdUser());
        user.setBalance(user.getBalance()+request.getMoney());
        userDb.save(user);
        var response = new UpdateBalanceResponse(request.getIdUser(), request.getMoney(), user.getBalance(), true);
        return response;
    }

    
    public List<Seller> getAllSeller() {
        return sellerDb.findAll(); 
    }

    public Seller updateRestSeller(Seller sellerDTO) {
        Seller seller = getSeller(sellerDTO.getIdUser());
        if (seller != null){
            if (!seller.getPassword().equals(sellerDTO.getPassword())) {
                seller.setNameUser(sellerDTO.getNameUser());
                seller.setUsername(sellerDTO.getUsername());
                seller.setPassword(sellerDTO.getPassword());
                seller.setEmail(sellerDTO.getEmail());
                seller.setAddress(sellerDTO.getAddress());
                seller.setCreatedAt(sellerDTO.getCreatedAt());
                seller.setUpdatedAt(LocalDateTime.now());
                sellerDb.save(seller);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password must be different from the old password");
            }
        }
        return seller;
    }

    public List<Customer> getAllCustomer() {
        return customerDb.findAll(); 
    }

    public Customer updateRestCustomer(Customer customerDTO) {
        Customer customer = getCustomer(customerDTO.getIdUser());
        if (customer != null) {
            if (!customer.getPassword().equals(customerDTO.getPassword())) {
                customer.setNameUser(customerDTO.getNameUser());
                customer.setUsername(customerDTO.getUsername());
                customer.setPassword(customerDTO.getPassword());
                customer.setEmail(customerDTO.getEmail());
                customer.setAddress(customerDTO.getAddress());
                customer.setCreatedAt(customerDTO.getCreatedAt());
                customer.setUpdatedAt(LocalDateTime.now());
                customer.setCartId(customerDTO.getCartId());
                customerDb.save(customer);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password must be different from the old password");
            }
        }
        return customer;
    }
>>>>>>> origin/development
}
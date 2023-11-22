package apapedia.user.restservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apapedia.user.model.Customer;
import apapedia.user.model.Seller;
import apapedia.user.repository.CustomerDb;
import apapedia.user.repository.SellerDb;
import jakarta.transaction.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


@Service
@Transactional
public class UserRestService {
    @Autowired
    private SellerDb sellerDb;

    @Autowired
    private CustomerDb customerDb;

    public void createRestSeller(Seller seller){
        seller.setCreatedAt(LocalDateTime.now());
        seller.setUpdatedAt(LocalDateTime.now());
        sellerDb.save(seller);
    }

    public Seller getSeller(UUID idSeller){
        return sellerDb.findSellerByIdUser(idSeller);
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

    public void createRestCustomer(Customer customer){
        LocalDateTime now = LocalDateTime.now();
        customer.setCreatedAt(now);
        customer.setUpdatedAt(now);
        customerDb.save(customer);
    }

    public Customer getCustomer(UUID idCustomer){
        return customerDb.findCustomerByIdUser(idCustomer);
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
}
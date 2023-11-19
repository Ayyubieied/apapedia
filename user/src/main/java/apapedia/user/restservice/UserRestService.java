package apapedia.user.restservice;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apapedia.user.model.Customer;
import apapedia.user.model.Seller;
import apapedia.user.repository.CustomerDb;
import apapedia.user.repository.SellerDb;
import jakarta.transaction.Transactional;

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

    public void createRestCustomer(Customer customer){
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        customerDb.save(customer);
    }

    public Customer getCustomer(UUID idSeller){
        return customerDb.findCustomerByIdUser(idSeller);
    }
}
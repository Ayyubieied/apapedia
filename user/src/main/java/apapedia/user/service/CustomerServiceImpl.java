package main.java.apapedia.user.service;

import main.java.apapedia.user.repository.CustomerDb;
import main.java.apapedia.user.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDb customerDb;

    @Override
    public List<Customer> getAllCustomer() {
        return customerDb.findAll(); 
    }

    @Override
    public Customer getCustomerById(UUID id) {
        for (Customer customer : getAllCustomer()) {
            if (customer.getIdCustomer().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer editCustomer(Customer customerDTO) {
        Customer customer = getCustomerById(customerDTO.getIdCustomer());
        if (customer != null){
            customer.setName(customerDTO.getName());
            customer.setUsername(customerDTO.getUsername());
            customer.setPassword(customerDTO.getPassword());
            customer.setEmail(customerDTO.getEmail());
            customer.setAddress(customerDTO.getAddress());
            customerDb.save(customer);
        }
        return customer;
    }

}
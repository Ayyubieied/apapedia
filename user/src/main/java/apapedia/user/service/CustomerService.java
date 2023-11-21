package main.java.apapedia.user.service;

import main.java.apapedia.user.model.Customer;
import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> getAllCustomer();
    Customer getCustomerById(UUID id);
    Customer editCustomer(Customer customer);
}

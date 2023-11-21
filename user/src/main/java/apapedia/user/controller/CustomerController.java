package main.java.apapedia.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

import main.java.apapedia.user.DTO.CustomerMapper;
import main.java.apapedia.user.DTO.request.EditCustomerRequestDTO;
import main.java.apapedia.user.model.Customer;
import main.java.apapedia.user.service.CustomerService;
import java.util.UUID;

@Controller
public class CustomerController {
    
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerService customerService;

    @GetMapping("customer/edit/{id}")
    public String formEditCustomer(@PathVariable("id") UUID id, Model model) {
        var customer = customerService.getCustomerById(id);
        var customerDTO = customerMapper.customerToEditCustomerRequestDTO(customer);
        model.addAttribute("customerDTO", customerDTO);
        return "edit-customer-page";
    }

    @PostMapping("customer/edit")
    public String editCustomer(@Valid @ModelAttribute EditCustomerRequestDTO customerDTO, Model model) {
        var customerFromDTO = customerMapper.editCustomerRequestDTOToCustomer(customerDTO);
        var customer = customerService.editCustomer(customerFromDTO);
        model.addAttribute("id", customer.getIdCustomer());
        return "success-edit-customer";
    }
}

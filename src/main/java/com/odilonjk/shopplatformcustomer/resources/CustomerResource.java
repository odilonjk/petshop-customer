package com.odilonjk.shopplatformcustomer.resources;

import com.odilonjk.shopplatformcustomer.entities.Customer;
import com.odilonjk.shopplatformcustomer.exceptions.CustomerNotFoundException;
import com.odilonjk.shopplatformcustomer.exceptions.IncompleteCustomerException;
import com.odilonjk.shopplatformcustomer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{id}")
    public Customer findById(@PathVariable String id) throws CustomerNotFoundException {
        return customerService.findById(id);
    }

    @GetMapping("/customer/name/{name}")
    public List<Customer> findByName(@PathVariable String name) throws CustomerNotFoundException {
        return customerService.findByName(name);
    }

    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) throws IncompleteCustomerException {
        return customerService.create(customer);
    }

}

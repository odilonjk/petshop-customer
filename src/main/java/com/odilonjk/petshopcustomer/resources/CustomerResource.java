package com.odilonjk.petshopcustomer.resources;

import com.odilonjk.petshopcustomer.entities.Customer;
import com.odilonjk.petshopcustomer.exceptions.CustomerNotFoundException;
import com.odilonjk.petshopcustomer.exceptions.IncompleteCustomerException;
import com.odilonjk.petshopcustomer.services.CustomerService;
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

package com.odilonjk.shopplatformcustomer.services;

import com.odilonjk.shopplatformcustomer.entities.Customer;
import com.odilonjk.shopplatformcustomer.exceptions.CustomerNotFoundException;
import com.odilonjk.shopplatformcustomer.exceptions.IncompleteCustomerException;
import com.odilonjk.shopplatformcustomer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findById(String id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
    }

    public List<Customer> findByName(String name) {
        return customerRepository.findByNameIgnoreCaseContaining(name);
    }

    public Customer create(Customer customer) throws IncompleteCustomerException {
        if (StringUtils.isEmpty(customer.getName())) {
            throw new IncompleteCustomerException("The customer must have a name.");
        }
        return customerRepository.save(customer);
    }
}

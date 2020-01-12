package com.odilonjk.shopplatformcustomer.services;

import com.odilonjk.shopplatformcustomer.entities.Customer;
import com.odilonjk.shopplatformcustomer.exceptions.CustomerNotFoundException;
import com.odilonjk.shopplatformcustomer.exceptions.IncompleteCustomerException;
import com.odilonjk.shopplatformcustomer.repositories.CustomerRepository;
import com.odilonjk.shopplatformcustomer.repositories.CustomerSearchPageRepository;
import com.odilonjk.shopplatformcustomer.repositories.CustomerSearchSliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerSearchSliceRepository searchRepository;

    @Autowired
    private CustomerSearchPageRepository searchPageRepository;

    public Customer findById(UUID id) throws CustomerNotFoundException {
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

    public Slice<Customer> sliceNameResult(Pageable pageable, String name) {
        return searchRepository.findByName(name,pageable);
    }

    public Page<Customer> pageNameResult(Pageable pageable, String name) {
        return searchPageRepository.findByName(name,pageable);
    }
}

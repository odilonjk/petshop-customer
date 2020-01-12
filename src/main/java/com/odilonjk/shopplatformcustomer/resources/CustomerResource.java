package com.odilonjk.shopplatformcustomer.resources;

import com.odilonjk.shopplatformcustomer.entities.Customer;
import com.odilonjk.shopplatformcustomer.exceptions.CustomerNotFoundException;
import com.odilonjk.shopplatformcustomer.exceptions.IncompleteCustomerException;
import com.odilonjk.shopplatformcustomer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/customer/name/{name}")//atributos devem ser buscado por pathVariable ?
    public List<Customer> findByName(@PathVariable String name) throws CustomerNotFoundException {
        return customerService.findByName(name);
    }

    @GetMapping(value = "/customerSlice")
    // atributos devem ser condicionais de busca pelo recurso. teste de retorno usando slice
    public ResponseEntity<Slice<Customer>> findByNameAttributeSlice(
            Pageable pageable,
            @RequestParam("name") String name
            ){
        var customerSlice = customerService.sliceNameResult(pageable, name);
        return new ResponseEntity<Slice<Customer>>(customerSlice, HttpStatus.OK);
    }

    @GetMapping(value = "/customerPage")// atributos devem ser condicionais de busca pelo recurso. teste de retorno usando slice
    public ResponseEntity<Page<Customer>> findByNameAttributePage(
            Pageable pageable,
            @RequestParam("name") String name
    ){
        var customerSlice = customerService.pageNameResult(pageable, name);
        return new ResponseEntity<Page<Customer>>(customerSlice, HttpStatus.OK);
    }


    @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) throws IncompleteCustomerException {
        return customerService.create(customer);
    }

}

package com.odilonjk.petshopcustomer.services;

import com.odilonjk.petshopcustomer.entities.Customer;
import com.odilonjk.petshopcustomer.exceptions.CustomerNotFoundException;
import com.odilonjk.petshopcustomer.exceptions.IncompleteCustomerException;
import com.odilonjk.petshopcustomer.repositories.CustomerRepository;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    public static final int THREE_CUSTOMERS = 3;
    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void shouldFindCustomerById_whenCustomerExistsOnDatabase() throws CustomerNotFoundException {
        var id = "a1B2c3";
        var customer = new Customer(id, "Tester", "+55 11 1234-5678");

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));

        var customerFound = customerService.findById(id);

        assertEquals(customer, customerFound);
    }

    @Test(expected = CustomerNotFoundException.class)
    public void shouldThrowException_whenCustomerDoesNotExistOnDatabase() throws CustomerNotFoundException {
        var id = "a1B2c3";

        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        customerService.findById(id);
    }

    @Test
    public void shouldFindCustomersByName_whenThereIsCustomersWithNameAlikeOnDatabase() throws CustomerNotFoundException {
        var id1 = "a1B2c3";
        var id2 = "a2B2c3";
        var id3 = "a3B2c3";
        var customer1 = new Customer(id1, "Tester A", "+55 11 1234-5678");
        var customer2 = new Customer(id2, "Tester B", "+55 11 2234-5678");
        var customer3 = new Customer(id3, "Tester C", "+55 11 3234-5678");

        when(customerRepository.findByNameIgnoreCaseContaining("tester"))
                .thenReturn(List.of(customer1, customer2, customer3));

        List<Customer> customersFound = customerService.findByName("tester");

        assertFalse(customersFound.isEmpty());
        assertTrue(customersFound.contains(customer1));
        assertTrue(customersFound.contains(customer2));
        assertTrue(customersFound.contains(customer3));
    }

    @Test
    public void shouldNotFindCustomersByName_whenThereIsNoCustomersWithNameAlikeOnDatabase() throws CustomerNotFoundException {
        when(customerRepository.findByNameIgnoreCaseContaining("tester"))
                .thenReturn(Lists.emptyList());

        List<Customer> customersFound = customerService.findByName("tester");

        assertTrue(customersFound.isEmpty());
    }

    @Test
    public void shouldReturnCustomer_whenCustomerHasBeenPersisted() throws IncompleteCustomerException {
        var id = "a1B2c3";
        var name = "Tester";
        var phone = "+55 11 1234-5678";
        var customer = new Customer(name, phone);
        var persistedCustomer = new Customer(id, name, phone);

        when(customerRepository.save(customer)).thenReturn(persistedCustomer);

        var receivedCustomer = customerService.create(customer);

        assertNotNull(receivedCustomer);
        assertEquals(id, receivedCustomer.getId());
        assertEquals(name, receivedCustomer.getName());
        assertEquals(phone, receivedCustomer.getPhone());
    }

    @Test(expected = IncompleteCustomerException.class)
    public void shouldThrowException_whenTryToCreateCustomerWithEmptyName() throws IncompleteCustomerException {
        var customer = new Customer("","", "");

        customerService.create(customer);
    }

    @Test(expected = IncompleteCustomerException.class)
    public void shouldThrowException_whenTryToCreateCustomerWithNullName() throws IncompleteCustomerException {
        var customer = new Customer();

        customerService.create(customer);
    }

}

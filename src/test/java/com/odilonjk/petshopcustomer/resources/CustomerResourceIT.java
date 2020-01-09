package com.odilonjk.petshopcustomer.resources;

import com.odilonjk.petshopcustomer.entities.Customer;
import com.odilonjk.petshopcustomer.repositories.CustomerRepository;
import com.odilonjk.petshopcustomer.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceIT {

    @LocalServerPort
    int port;

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void shouldFindCustomerById() {
        var id = "a1b2c3";
        var customer = new Customer(id, "Tester", "(55) 1234-5678");

        when(customerRepository.findById(id))
                .thenReturn(Optional.of(customer));

        var receivedCustomer = given()
                .when().port(port).get("/customer/" + id)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(Customer.class);

        assertEquals(customer, receivedCustomer);
    }

    @Test
    public void shouldFindCustomerByName() {
        var customer1 = new Customer("a1b2c3", "Tester A", "(55) 1234-5678");
        var customer2 = new Customer("a2b2c3", "Tester B", "(55) 2234-5678");
        var customer3 = new Customer("a3b2c3", "Tester C", "(55) 3234-5678");

        when(customerRepository.findByNameIgnoreCaseContaining("Tester"))
                .thenReturn(List.of(customer1, customer2, customer3));

        Customer[] receivedCustomers = given()
                .when().port(port).get("/customer/name/" + "Tester")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(Customer[].class);

        assertArrayEquals(new Customer[]{customer1, customer2, customer3}, receivedCustomers);
    }

}

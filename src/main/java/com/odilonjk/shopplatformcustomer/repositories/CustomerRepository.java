package com.odilonjk.shopplatformcustomer.repositories;

import com.odilonjk.shopplatformcustomer.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public List<Customer> findByNameIgnoreCaseContaining(String name);

}

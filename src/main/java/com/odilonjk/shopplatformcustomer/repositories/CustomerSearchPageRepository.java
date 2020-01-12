package com.odilonjk.shopplatformcustomer.repositories;

import com.odilonjk.shopplatformcustomer.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerSearchPageRepository extends PagingAndSortingRepository<Customer,Integer> {

    Page<Customer> findByName(String name, Pageable pageable);
}

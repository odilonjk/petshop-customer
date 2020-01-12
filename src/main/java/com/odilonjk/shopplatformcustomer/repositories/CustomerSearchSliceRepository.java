package com.odilonjk.shopplatformcustomer.repositories;

import com.odilonjk.shopplatformcustomer.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerSearchSliceRepository extends PagingAndSortingRepository<Customer, UUID>{

    Slice<Customer> findByName(String name, Pageable pageable);

}
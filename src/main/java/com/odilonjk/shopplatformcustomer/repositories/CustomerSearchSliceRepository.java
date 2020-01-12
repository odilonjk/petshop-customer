package com.odilonjk.shopplatformcustomer.repositories;

import com.odilonjk.shopplatformcustomer.entities.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomerSearchSliceRepository extends PagingAndSortingRepository<Customer,Integer>{

    Slice<Customer> findByName(String name, Pageable pageable);

}
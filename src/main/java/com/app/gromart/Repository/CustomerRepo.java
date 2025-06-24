package com.app.gromart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.gromart.Entity.Customer;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

    
}

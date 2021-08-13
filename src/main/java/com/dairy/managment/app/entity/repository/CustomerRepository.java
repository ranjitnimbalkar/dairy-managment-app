package com.dairy.managment.app.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairy.managment.app.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

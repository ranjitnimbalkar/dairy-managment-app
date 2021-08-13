package com.dairy.managment.app.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairy.managment.app.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}

package com.balinder.payment.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balinder.payment.payment.entity.Payments;

@Repository
public interface PaymentRepo extends JpaRepository<Payments, Long>{

}

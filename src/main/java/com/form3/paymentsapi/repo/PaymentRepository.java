package com.form3.paymentsapi.repo;

import com.form3.paymentsapi.model.Payment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends ReactiveCrudRepository<Payment, String> {

}
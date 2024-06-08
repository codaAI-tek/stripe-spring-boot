package com.javawhizz.stripePayment.Repository;
import com.javawhizz.stripePayment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

 
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
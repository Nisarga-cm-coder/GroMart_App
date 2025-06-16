package com.app.gromart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.gromart.Entity.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {

    //  Search payments by payment mode (case insensitive)
    @Query("SELECT p FROM Payment p WHERE LOWER(p.paymentMode) = LOWER(:mode)")
    List<Payment> findByPaymentModeIgnoreCase(@Param("mode") String mode);

    // Sort payments by payment date descending (latest first)
    @Query("SELECT p FROM Payment p ORDER BY p.paymentDate DESC")
    List<Payment> findAllOrderByPaymentDateDesc();

    // Fetch only payments where customer is not null
    @Query("SELECT p FROM Payment p WHERE p.customer IS NOT NULL")
    List<Payment> findAllWithCustomer();

    // Fetch only payments with customer, sorted by date
    @Query("SELECT p FROM Payment p WHERE p.customer IS NOT NULL ORDER BY p.paymentDate DESC")
    List<Payment> findAllWithCustomerOrderByDateDesc();
}

package com.app.gromart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.gromart.Entity.Payment;
import com.app.gromart.Repository.PaymentRepo;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    public Payment createPayment(Payment payment) {
        return paymentRepo.save(payment);
    }

    public Payment getPayment(Long id) {
        return paymentRepo.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    // Returns payments with valid customers
    public List<Payment> getAllPayments() {
        return paymentRepo.findAllWithCustomerOrderByDateDesc();
    }

    // ✅ Paginated + sorted payments
    public Page<Payment> getPaginatedPayments(Pageable pageable) {
        return paymentRepo.findAll(pageable);
    }

    public Payment updatePayment(Long id, Payment updatedPayment) {
        Payment existing = getPayment(id);
        existing.setPaymentMode(updatedPayment.getPaymentMode());
        existing.setPaymentDate(updatedPayment.getPaymentDate());
        existing.setPaymentStatus(updatedPayment.getPaymentStatus());
        return paymentRepo.save(existing);
    }

    public void deletePayment(Long id) {
        paymentRepo.deleteById(id);
    }
}

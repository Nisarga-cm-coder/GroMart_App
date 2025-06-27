package com.app.gromart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.gromart.Entity.Payment;
import com.app.gromart.Service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Create payment
    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment payment){
        return new ResponseEntity<>(paymentService.createPayment(payment), HttpStatus.CREATED);
    }

    // Get payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> get(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    // Get all payments (non-paginated)
    @GetMapping
    public List<Payment> getAll(){
        return paymentService.getAllPayments();
    }

    //  Get paginated + sorted payments
    //http://localhost:8081/api/payment/paginated?page=0&size=5&sort=price,desc
    @GetMapping("/paginated")
    public ResponseEntity<Page<Payment>> getPaginatedPayments(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "paymentDate") String sortBy,
        @RequestParam(defaultValue = "desc") String direction
    ) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                     Sort.by(sortBy).descending() :
                     Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Payment> payments = paymentService.getPaginatedPayments(pageable);
        return ResponseEntity.ok(payments);
    }

    // Update payment
    @PutMapping("/put/{id}")
    public ResponseEntity<Payment> update(@PathVariable Long id, @RequestBody Payment payment){
        return ResponseEntity.ok(paymentService.updatePayment(id, payment));
    }

    // Delete payment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}

package com.app.gromart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.app.gromart.Entity.Payment;
import com.app.gromart.Service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // ✅ Admin-only: Create payment
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Payment> create(@RequestBody Payment payment){
        return new ResponseEntity<>(paymentService.createPayment(payment), HttpStatus.CREATED);
    }

    //both admin and user
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Payment> get(@PathVariable Long id){
        return ResponseEntity.ok(paymentService.getPayment(id));
    }

    //both admin and User
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<Payment> getAll(){
        return paymentService.getAllPayments();
    }

    // both User and ADMIN
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
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

    // ✅ Admin-only: Update payment
    @PutMapping("/put/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Payment> update(@PathVariable Long id, @RequestBody Payment payment){
        return ResponseEntity.ok(paymentService.updatePayment(id, payment));
    }

    // ✅ Admin-only: Delete payment
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}

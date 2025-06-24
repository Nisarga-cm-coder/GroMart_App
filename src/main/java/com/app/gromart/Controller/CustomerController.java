package com.app.gromart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.app.gromart.Entity.Customer;
import com.app.gromart.Service.CustomerService;

@RestController
@RequestMapping("/api/Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ✅ Admin-only: Create customer
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    // 🔓 Public (authenticated): Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> get(@PathVariable Long id){
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    // ADMIN ONLY GET ALL CUSTOMERS
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Customer> getAll(){
        return customerService.getAllCustomers();
    }

    // ✅ Admin-only: Update customer
    @PutMapping("/put/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer customer){
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    // ✅ Admin-only: Delete customer
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}

package com.app.gromart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.gromart.Entity.Customer;
import com.app.gromart.Repository.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer getCustomer(Long id) {
        return customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existing = getCustomer(id);
        existing.setFullName(updatedCustomer.getFullName());
        existing.setEmail(updatedCustomer.getEmail());
        existing.setPhone(updatedCustomer.getPhone());
        existing.setAddress(updatedCustomer.getAddress());
        return customerRepo.save(existing);
    }

    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }

}

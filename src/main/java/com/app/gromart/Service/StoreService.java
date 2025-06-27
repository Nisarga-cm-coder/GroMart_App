package com.app.gromart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.gromart.Entity.Store;
import com.app.gromart.Repository.StoreRepo;

@Service
public class StoreService {

    @Autowired
    private StoreRepo storeRepo;

    // Create a new store
    public Store createStore(Store store) {
        return storeRepo.save(store);
    }

    // Get a single store by ID
    public Store getStore(Long id) {
        return storeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found with id: " + id));
    }

    // Get all stores
    public List<Store> getAllStores() {
        return storeRepo.findAll();
    }

    // Update store by ID
    public Store updateStore(Long id, Store updatedStore) {
        Store existing = getStore(id);
        existing.setStoreName(updatedStore.getStoreName());
        existing.setEmail(updatedStore.getEmail());
        existing.setLocation(updatedStore.getLocation());
        existing.setContactNumber(updatedStore.getContactNumber());
        return storeRepo.save(existing);
    }

    // Delete store by ID
    public void deleteStore(Long id) {
        storeRepo.deleteById(id);
    }
}

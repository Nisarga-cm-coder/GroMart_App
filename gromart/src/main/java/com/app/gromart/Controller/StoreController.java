package com.app.gromart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.gromart.Entity.Store;
import com.app.gromart.Service.StoreService;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // Get all stores (no pagination)
    @GetMapping
    public List<Store> getAllStore() {
        return storeService.getAllStores();
    }

    // Create a new store
    @PostMapping
    public ResponseEntity<Store> create(@RequestBody Store store) {
        Store created = storeService.createStore(store);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get a store by ID
    @GetMapping("/{id}")
    public ResponseEntity<Store> get(@PathVariable Long id) {
        Store store = storeService.getStore(id);
        return ResponseEntity.ok(store);
    }

    // Update a store by ID
    @PutMapping("/{id}")
    public ResponseEntity<Store> update(@PathVariable Long id, @RequestBody Store store) {
        Store updated = storeService.updateStore(id, store);
        return ResponseEntity.ok(updated);
    }

    // Delete a store by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storeService.deleteStore(id);
        return ResponseEntity.noContent().build();
    }
}

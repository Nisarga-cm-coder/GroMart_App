package com.app.gromart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.app.gromart.Entity.OrderItem;
import com.app.gromart.Service.OrderItemService;

@RestController
@RequestMapping("/api/OrderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    // ✅ Admin-only: Create
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem orderItem){
        return new ResponseEntity<>(orderItemService.createOrderItem(orderItem), HttpStatus.CREATED);
    }

    // 🔓 Public: Get by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> get(@PathVariable Long id){
        return ResponseEntity.ok(orderItemService.getOrderItem(id));
    }

    // 🔓 Public: Get all order items
    @GetMapping
    public List<OrderItem> getAll(){
        return orderItemService.getAllOrderItems();
    }

    // ✅ Admin-only: Update
    @PutMapping("/put/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody OrderItem orderItem){
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItem));
    }

    // ✅ Admin-only: Delete
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}

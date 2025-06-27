package com.app.gromart.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.gromart.Entity.OrderItem;
import com.app.gromart.Service.OrderItemService;


@RestController
@RequestMapping("/api/OrderItem")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem orderItem){
        return new ResponseEntity<>(orderItemService.createOrderItem(orderItem), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> get(@PathVariable Long id){
        return ResponseEntity.ok(orderItemService.getOrderItem(id));
    }

    @GetMapping
    public List<OrderItem> getAll(){
        return orderItemService.getAllOrderItems();
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody OrderItem orderItem){
        return ResponseEntity.ok(orderItemService.updateOrderItem(id, orderItem));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
    
}

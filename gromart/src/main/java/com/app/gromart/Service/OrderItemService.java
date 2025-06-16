package com.app.gromart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.gromart.Entity.OrderItem;
import com.app.gromart.Repository.OrderItemRepo;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepo orderItemRepo;

    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    public OrderItem getOrderItem(Long id) {
        return orderItemRepo.findById(id).orElseThrow(() -> new RuntimeException("OrderItem not found"));
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepo.findAll();
    }

    public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
        OrderItem existing = getOrderItem(id);
        existing.setId(updatedOrderItem.getId()); // assuming @ManyToOne Order
        existing.setQuantity(updatedOrderItem.getQuantity());
        existing.setTotalPrice(updatedOrderItem.getTotalPrice()); // or getOrderStatus() if that's the field
        return orderItemRepo.save(existing);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepo.deleteById(id);
    }
}

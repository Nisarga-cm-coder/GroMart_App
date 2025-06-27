package com.app.gromart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // ✅ Required for named parameters
import org.springframework.stereotype.Repository;

import com.app.gromart.Entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

    // Find all order items with quantity greater than a given number
    @Query("SELECT o FROM OrderItem o WHERE o.quantity > :minQuantity")
    List<OrderItem> findByQuantityGreaterThan(@Param("minQuantity") int minQuantity);

    //  Sort all order items by total price descending
    @Query("SELECT o FROM OrderItem o ORDER BY o.totalPrice DESC")
    List<OrderItem> findAllOrderByTotalPriceDesc();
}

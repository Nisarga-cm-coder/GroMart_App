package com.app.gromart.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // ✅ Required for @Param
import org.springframework.stereotype.Repository;

import com.app.gromart.Entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    // Search by product name (case-insensitive, partial match)
    @Query("SELECT p FROM Product p WHERE LOWER(p.productName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByProductNameContainingIgnoreCase(@Param("name") String name);

    // Sort all products by price in ascending order
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> findAllOrderByPriceASC();
}

package com.app.gromart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.gromart.Entity.Product;
import com.app.gromart.Repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    // Create a new product
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

    // Get a single product by ID
    public Product getProduct(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // Get all products (non-paginated)
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Get all products (paginated + sorted)
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    // Update product by ID
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProduct(id);
        existing.setProductName(updatedProduct.getProductName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setPrice(updatedProduct.getPrice());
        existing.setInstock(updatedProduct.isInstock());
        return productRepo.save(existing);
    }

    // Delete product by ID
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}

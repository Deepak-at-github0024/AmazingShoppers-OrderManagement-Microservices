package com.example.product_service.Repository;

import com.example.product_service.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    boolean existsByNameAndCategory(String name, String category);
}

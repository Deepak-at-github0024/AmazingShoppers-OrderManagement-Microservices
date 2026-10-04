package com.example.order_service.Repository;

import com.example.order_service.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long id);

    void deleteByUserId(Long userId);

    void deleteByProductId(Long productId);

    boolean existsByProductId(Long productId);

    boolean existsByUserId(Long userId);
}

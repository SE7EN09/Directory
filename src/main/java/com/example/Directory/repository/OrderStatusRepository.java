package com.example.directory.repository;

import com.example.directory.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
  OrderStatus findByName(String name);
}

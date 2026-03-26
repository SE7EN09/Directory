package com.example.directory.repository;

import com.example.directory.entity.CartItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {
  List<CartItem> findByClientId(Long clientId);
}

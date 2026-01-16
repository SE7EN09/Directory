package com.example.Directory.repository;

import com.example.Directory.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByClientId(Long clientId);

}

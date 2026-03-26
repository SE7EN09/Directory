package com.example.directory.service;

import com.example.directory.dto.ProductRequest;
import com.example.directory.entity.Product;
import com.example.directory.entity.User;
import com.example.directory.repository.ProductRepository;
import com.example.directory.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  public Product add(ProductRequest request) {
    User seller = getCurrentUser();
    Product product =
        Product.builder()
            .name(request.getName())
            .price(request.getPrice())
            .quantity(request.getQuantity())
            .available(request.isAvailable())
            .seller(seller)
            .createdAt(LocalDateTime.now())
            .build();
    return productRepository.save(product);
  }

  public Product edit(Long productId, ProductRequest request) {
    Product product =
        productRepository
            .findById(productId)
            .orElseThrow(() -> new RuntimeException("Товар не найден"));
    product.setName(request.getName());
    product.setPrice(request.getPrice());
    product.setQuantity(request.getQuantity());
    return productRepository.save(product);
  }

  private User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String phone = authentication.getName();
    return userRepository
        .findByPhone(phone)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }
}

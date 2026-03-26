package com.example.directory.service;

import com.example.directory.dto.CartRequest;
import com.example.directory.entity.CartItem;
import com.example.directory.entity.Product;
import com.example.directory.entity.User;
import com.example.directory.repository.CartRepository;
import com.example.directory.repository.ProductRepository;
import com.example.directory.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

  private final CartRepository cartRepository;
  private final ProductRepository productRepository;
  private final UserRepository userRepository;

  public List<CartItem> getCart() {
    User user = getCurrentUser();
    return cartRepository.findByClientId(user.getId());
  }

  public void remove(Long cartItemId) {
    cartRepository.deleteById(cartItemId);
  }

  public void add(CartRequest request) {
    User client = getCurrentUser();

    Product product =
        productRepository
            .findById(request.getProductId())
            .orElseThrow(() -> new RuntimeException("Товар не найден"));

    if (!product.isAvailable() || product.getQuantity() < request.getQuantity()) {
      throw new RuntimeException("Недостаточно товара");
    }

    CartItem item =
        CartItem.builder()
            .client(client)
            .product(product)
            .quantity(request.getQuantity())
            .createdAt(LocalDateTime.now())
            .build();
    cartRepository.save(item);
  }

  private User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String phone = authentication.getName();
    return userRepository
        .findByPhone(phone)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }
}

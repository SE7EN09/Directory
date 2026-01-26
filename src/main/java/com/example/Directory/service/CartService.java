package com.example.Directory.service;

import com.example.Directory.dto.CartRequest;
import com.example.Directory.entity.CartItem;
import com.example.Directory.entity.Product;
import com.example.Directory.entity.User;
import com.example.Directory.repository.CartRepository;
import com.example.Directory.repository.ProductRepository;
import com.example.Directory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepo;

    public List<CartItem> getCart(Long clientId) {
        return cartRepo.findByClientId(clientId);

    }

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public void add(Long clientId, CartRequest request) {

        User client = userRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Клиент не найден"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Товар не найден"));

        if (!product.isAvailable() || product.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Недостаточно товара");

        }

        CartItem item = CartItem.builder()
                .client(client)
                .product(product)
                .quantity(request.getQuantity())
                .createdAt(LocalDateTime.now())
                .build();
        cartRepository.save(item);
    }
}

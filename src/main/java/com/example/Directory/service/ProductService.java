package com.example.Directory.service;

import com.example.Directory.dto.ProductRequest;
import com.example.Directory.entity.Product;
import com.example.Directory.entity.User;
import com.example.Directory.repository.ProductRepository;
import com.example.Directory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Product add(Long sellerId, ProductRequest request) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Продавец не найден"));
        Product product = Product.builder()
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
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return productRepository.save(product);
    }
}

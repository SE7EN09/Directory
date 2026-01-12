package com.example.Directory.service;

import com.example.Directory.dto.ProductRequest;
import com.example.Directory.entity.Product;
import com.example.Directory.entity.User;
import com.example.Directory.repository.ProductRepository;
import com.example.Directory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public Product addProduct(Long userId, ProductRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!user.isCanAddProducts()) {
            throw new RuntimeException("Администратор не разрешил добавление товаров");
        }

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .available(request.isAvailable())
                .owner(user)
                .build();

        return productRepository.save(product);
    }

    public List<Product> getMyProducts(Long userId) {
        return productRepository.findByOwnerId(userId);
    }
}

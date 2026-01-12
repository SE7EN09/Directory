package com.example.Directory.controller;

import com.example.Directory.dto.ProductRequest;
import com.example.Directory.dto.UserRegisterRequest;
import com.example.Directory.entity.Product;
import com.example.Directory.entity.User;
import com.example.Directory.service.ProductService;
import com.example.Directory.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProductService productService;

    @PostMapping("/register")
    public User register(@RequestBody @Valid UserRegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/{userId}/product")
    public Product addProduct(
            @PathVariable Long userId,
            @RequestBody ProductRequest request
    ) {
        return productService.addProduct(userId, request);
    }

    @GetMapping("/{userId}/products")
    public List<Product> myProducts(@PathVariable Long userId) {
        return productService.getMyProducts(userId);
    }
}

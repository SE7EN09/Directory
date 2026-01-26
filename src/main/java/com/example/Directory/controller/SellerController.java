package com.example.Directory.controller;

import com.example.Directory.dto.ProductRequest;
import com.example.Directory.entity.Product;
import com.example.Directory.service.OrderService;
import com.example.Directory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {

    private final ProductService productService;
    private final OrderService orderService;

    @PostMapping("/{sellerId}/product")
    public Product addProduct(@PathVariable Long sellerId, @RequestBody ProductRequest request) {
        return productService.add(sellerId, request);

    }

    @PutMapping("/product/{productId}")
    public Product editProduct(@PathVariable Long productId, @RequestBody ProductRequest request) {
        return productService.edit(productId, request);

    }

    @PostMapping("/order/{orderId}/deliver")
    public void deliver(@PathVariable Long orderId) {
        orderService.deliver(orderId);
    }
}

package com.example.directory.controller;

import com.example.directory.dto.ProductRequest;
import com.example.directory.entity.Product;
import com.example.directory.service.OrderService;
import com.example.directory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {

  private final ProductService productService;
  private final OrderService orderService;

  @PostMapping("/product")
  public Product addProduct(@RequestBody ProductRequest request) {
    return productService.add(request);
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

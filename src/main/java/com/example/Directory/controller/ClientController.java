package com.example.directory.controller;

import com.example.directory.dto.CartRequest;
import com.example.directory.entity.CartItem;
import com.example.directory.entity.Order;
import com.example.directory.service.CartService;
import com.example.directory.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

  private final CartService cartService;
  private final OrderService orderService;

  @GetMapping("/cart")
  public List<CartItem> getCart() {
    return cartService.getCart();
  }

  @PostMapping("/cart")
  public void addToCart(@RequestBody CartRequest request) {
    cartService.add(request);
  }

  @PostMapping("/order")
  public Order createOrder() {
    return orderService.create();
  }

  @PostMapping("/order/{orderId}/cancel")
  public void cancel(@PathVariable Long orderId) {
    orderService.cancel(orderId);
  }
}

package com.example.directory.controller;

import com.example.directory.dto.CartRequest;
import com.example.directory.entity.CartItem;
import com.example.directory.service.CartService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

  private final CartService cartService;

  @PostMapping("/add")
  public void add(@RequestBody CartRequest request) {
    cartService.add(request);
  }

  @GetMapping
  public List<CartItem> getCart() {
    return cartService.getCart();
  }

  @DeleteMapping("/remove/{cartItemId}")
  public void remove(@PathVariable Long cartItemId) {
    cartService.remove(cartItemId);
  }
}

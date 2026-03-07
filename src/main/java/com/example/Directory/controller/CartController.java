package com.example.Directory.controller;

import com.example.Directory.dto.CartRequest;
import com.example.Directory.entity.CartItem;
import com.example.Directory.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{clientId}/add")
    public void add(@PathVariable Long clientId,
                    @RequestBody CartRequest request) {
        cartService.add(clientId, request);

    }

    @GetMapping("/{clientId}")
    public List<CartItem> getCart(@PathVariable Long clientId) {
        return cartService.getCart(clientId);

    }

    @DeleteMapping("/remove/{cartItemId}")
    public void remove(@PathVariable Long cartItemId) {
        cartService.remove(cartItemId);

    }
}
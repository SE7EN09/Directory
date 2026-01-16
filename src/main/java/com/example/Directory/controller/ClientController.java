package com.example.Directory.controller;

import com.example.Directory.dto.CartRequest;
import com.example.Directory.entity.CartItem;
import com.example.Directory.entity.Order;
import com.example.Directory.service.CartService;
import com.example.Directory.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class ClientController {

    @GetMapping("/{id}/cart")
    public List<CartItem> cart(@PathVariable Long id) {
        return cartService.getCart(id);

    }



    private final CartService cartService;
    private final OrderService orderService;

    @PostMapping("/{clientId}/cart")
    public void addToCart(@PathVariable Long clientId,
                          @RequestBody CartRequest request) {
        cartService.add(clientId, request);
    }

    @PostMapping("/{clientId}/order")
    public Order createOrder(@PathVariable Long clientId) {
        return orderService.create(clientId);
    }

    @PostMapping("/order/{orderId}/cancel")
    public void cancel(@PathVariable Long orderId) {
        orderService.cancel(orderId);
    }
}

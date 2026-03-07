package com.example.Directory.controller;

import com.example.Directory.entity.Order;
import com.example.Directory.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create/{clientId}")
    public Order create(@PathVariable Long clientId) {
        return orderService.create(clientId);
    }

    @PostMapping("/{orderId}/cancel")
    public void cancel(@PathVariable Long orderId) {
        orderService.cancel(orderId);
    }

    @PostMapping("/deliver/{orderId}")
    public void deliver(@PathVariable Long orderId) {
        orderService.deliver(orderId);
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable Long orderId) {
        orderService.softDelete(orderId);
    }
}

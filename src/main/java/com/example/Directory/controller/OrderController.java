package com.example.directory.controller;

import com.example.directory.entity.Order;
import com.example.directory.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/create")
  public Order create() {
    return orderService.create();
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

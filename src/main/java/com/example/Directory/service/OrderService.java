package com.example.Directory.service;

import com.example.Directory.entity.CartItem;
import com.example.Directory.entity.Order;
import com.example.Directory.entity.OrderStatus;
import com.example.Directory.repository.CartRepository;
import com.example.Directory.repository.OrderRepository;
import com.example.Directory.repository.OrderStatusRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Getter
@Setter
@Builder
@AllArgsConstructor

public class OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderStatusRepository statusRepository;

    public Order create(Long clientId) {
        List<CartItem> items = cartRepository.findByClientId(clientId);

        if (items.isEmpty()) {
            throw new RuntimeException("Корзина пуста");

        }

        CartItem firstItem = items.getFirst();
        OrderStatus status = statusRepository.findByName("NEW");
        Order order = Order.builder()
                .client(firstItem.getClient())
                .seller(firstItem.getProduct().getSeller())
                .status(status)
                .createdAt(LocalDateTime.now())
                .build();
        cartRepository.deleteAll(items);
        return orderRepository.save(order);

    }

    public void cancel(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));

        order.setStatus(statusRepository.findByName("CANCELED"));
        orderRepository.save(order);

    }

    public void deliver(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ не найден"));

        order.setStatus(statusRepository.findByName("DELIVERED"));
        orderRepository.save(order);
    }
}

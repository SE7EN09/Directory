package com.example.directory.service;

import com.example.directory.entity.CartItem;
import com.example.directory.entity.Order;
import com.example.directory.entity.OrderStatus;
import com.example.directory.entity.User;
import com.example.directory.repository.CartRepository;
import com.example.directory.repository.OrderRepository;
import com.example.directory.repository.OrderStatusRepository;
import com.example.directory.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderService {

  private final CartRepository cartRepository;
  private final OrderRepository orderRepository;
  private final OrderStatusRepository statusRepository;
  private final UserRepository userRepository;

  public Order create() {
    User client = getCurrentUser();
    List<CartItem> items = cartRepository.findByClientId(client.getId());

    if (items.isEmpty()) {
      throw new RuntimeException("Корзина пуста");
    }

    CartItem firstItem = items.getFirst();
    OrderStatus status = statusRepository.findByName("NEW");
    Order order =
        Order.builder()
            .client(firstItem.getClient())
            .seller(firstItem.getProduct().getSeller())
            .status(status)
            .createdAt(LocalDateTime.now())
            .build();
    cartRepository.deleteAll(items);
    return orderRepository.save(order);
  }

  public void cancel(Long orderId) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new RuntimeException("Заказ не найден"));

    order.setStatus(statusRepository.findByName("CANCELED"));
    orderRepository.save(order);
  }

  public void softDelete(Long orderId) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new RuntimeException("Заказ не найден"));

    order.setDeleted(true);
    orderRepository.save(order);
  }

  public void deliver(Long orderId) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new RuntimeException("Заказ не найден"));

    order.setStatus(statusRepository.findByName("DELIVERED"));
    orderRepository.save(order);
  }

  private User getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String phone = authentication.getName();
    return userRepository
        .findByPhone(phone)
        .orElseThrow(() -> new RuntimeException("User not found"));
  }
}

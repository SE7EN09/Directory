package com.example.Directory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User client;

    @ManyToOne
    private User seller;

    @ManyToOne
    private OrderStatus status;
    @Builder.Default
    private boolean deleted = false;

    private LocalDateTime createdAt;
}

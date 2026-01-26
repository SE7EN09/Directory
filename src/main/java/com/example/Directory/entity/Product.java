package com.example.Directory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity

@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "seller_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private boolean available;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
}

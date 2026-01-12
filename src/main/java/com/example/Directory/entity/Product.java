package com.example.Directory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "products",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"name", "owner_id"}
        )
)
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

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}

package com.example.Directory.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private boolean available;
}

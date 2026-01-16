package com.example.Directory.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartRequest {
    private Long productId;
    private Integer quantity;
}

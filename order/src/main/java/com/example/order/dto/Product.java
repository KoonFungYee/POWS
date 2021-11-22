package com.example.order.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Product {
    private int id;
    private String productName;
    private BigDecimal price;
    private int stock;
}

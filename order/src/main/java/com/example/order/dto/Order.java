package com.example.order.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Order {
    private String id;
    private int pid;
    private BigDecimal totalAmt;
    private String status;
    private int quantity;
}

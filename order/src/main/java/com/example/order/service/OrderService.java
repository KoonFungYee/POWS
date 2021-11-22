package com.example.order.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.example.order.dto.Order;
import com.example.order.utils.RespResult;

public interface OrderService {
    RespResult insertNewOrder(Order order) throws IOException, TimeoutException;
}

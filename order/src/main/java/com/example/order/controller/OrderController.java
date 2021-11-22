package com.example.order.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSON;
import com.example.order.dto.Order;
import com.example.order.service.OrderService;
import com.example.order.utils.RespResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/submitOrder")
    public RespResult submitOrder(@RequestBody Order order) throws IOException, TimeoutException{
        System.out.println("order received: " + JSON.toJSONString(order));
        return orderService.insertNewOrder(order);
    }

}

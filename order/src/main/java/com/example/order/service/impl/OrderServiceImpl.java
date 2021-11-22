package com.example.order.service.impl;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.alibaba.fastjson.JSON;
import com.example.order.dao.OrderMapper;
import com.example.order.dto.Order;
import com.example.order.dto.Product;
import com.example.order.service.OrderService;
import com.example.order.service.ProductService;
import com.example.order.utils.RabbitUtils;
import com.example.order.utils.RespResult;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ProductService productService;

    @Autowired
    OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RespResult insertNewOrder(Order order) throws IOException, TimeoutException  {
        RespResult respResult;
        int availableQuantity = productService.checkQuantity(order.getPid());
        if (order.getQuantity() > availableQuantity) {
            respResult = new RespResult(500, "Quantity exceed the limit", null);
            return respResult;
        }
        order.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setStatus("New");
        orderMapper.insertNewOrder(order);
        Product product = new Product();
        product.setId(order.getPid());
        product.setStock(order.getQuantity());
        String message = JSON.toJSONString(product);
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish("stock", "update_stock", null, message.getBytes());
        channel.close();
        connection.close();
        respResult = new RespResult(200, "Inserted", null);
        return respResult;
    }
    
}

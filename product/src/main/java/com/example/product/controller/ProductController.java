package com.example.product.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;

import com.alibaba.fastjson.JSON;
import com.example.product.dto.Product;
import com.example.product.service.ProductService;
import com.example.product.utils.RabbitUtils;
import com.rabbitmq.client.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostConstruct
    public void updateStockQuantity() throws IOException {
        Connection connection = RabbitUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("update_stock", false, false, false, null);
        channel.queueBind("update_stock", "stock", "update_stock");
        channel.basicQos(1);
        channel.basicConsume("update_stock", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                Product product = JSON.parseObject(new String(body), Product.class);
                System.out.println("Update stock quantity for: " + JSON.toJSONString(product));
                productService.updateStockQuantity(product);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });
    }

}

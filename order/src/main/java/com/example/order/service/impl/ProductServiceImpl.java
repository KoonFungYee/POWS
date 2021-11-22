package com.example.order.service.impl;

import com.example.order.dao.ProductMapper;
import com.example.order.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Override
    public int checkQuantity(int pid) {
        return productMapper.getQuantity(pid);
    }
    
}

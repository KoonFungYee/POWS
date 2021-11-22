package com.example.product.service.impl;

import com.example.product.dao.ProductMapper;
import com.example.product.dto.Product;
import com.example.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateStockQuantity(Product product) {
        int quantity = productMapper.getStockQuantity(product.getId());
        product.setStock(quantity - product.getStock());
        productMapper.updateStockQuantity(product);
    }
    
}

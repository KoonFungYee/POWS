package com.example.product.dao;

import com.example.product.dto.Product;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductMapper {
    int getStockQuantity(int id);
    int updateStockQuantity(Product product);
}

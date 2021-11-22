package com.example.order.dao;

import com.example.order.dto.Order;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    int insertNewOrder(Order order);
}

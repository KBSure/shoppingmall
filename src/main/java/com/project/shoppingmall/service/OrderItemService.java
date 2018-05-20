package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Order;
import com.project.shoppingmall.domain.OrderItem;
import com.project.shoppingmall.domain.Product;

import java.util.List;

public interface OrderItemService {
    
    List<OrderItem> registOrderItems(Order order, List<Product> products, List<Integer> quantities);
    
}

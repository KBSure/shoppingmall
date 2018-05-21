package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Order;
import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.dto.OrderInfo;

import java.util.List;

public interface OrderService {
    
    Order registOrder(OrderInfo orderInfo);
    
    List<Order> getMemberOrders(Long memberId);
    
}

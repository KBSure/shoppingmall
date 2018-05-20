package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.OrderItem;

import java.util.List;

public interface OrderItemsRepositoryCustom {
    
    List<OrderItem> findAllOrderItems(Long orderId);
    
}

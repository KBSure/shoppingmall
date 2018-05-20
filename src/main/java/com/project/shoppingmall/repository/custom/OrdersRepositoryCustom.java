package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.Order;

import java.util.List;

public interface OrdersRepositoryCustom {
    
    List<Order> findMemberOrders(Long memberId);
    
}

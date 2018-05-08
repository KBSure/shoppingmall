package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.OrderItem;

public interface OrderItemsRepository extends JpaQueryDslPredicateRepository<OrderItem,Long> {
}

package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.OrderItem;
import com.project.shoppingmall.repository.custom.OrderItemsRepositoryCustom;
import com.project.shoppingmall.repository.custom.OrdersRepositoryCustom;

public interface OrderItemsRepository extends JpaQueryDslPredicateRepository<OrderItem,Long>, OrderItemsRepositoryCustom {

}

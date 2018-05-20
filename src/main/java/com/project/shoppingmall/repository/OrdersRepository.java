package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Order;
import com.project.shoppingmall.repository.custom.OrdersRepositoryCustom;

import java.util.List;

public interface OrdersRepository extends JpaQueryDslPredicateRepository<Order,Long>, OrdersRepositoryCustom {

    List<Order> findAllByMember(Member member);
    
}

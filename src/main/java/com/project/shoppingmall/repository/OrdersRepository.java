package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Order;

import java.util.List;

public interface OrdersRepository extends JpaQueryDslPredicateRepository<Order,Long>{

    List<Order> findAllByMember(Member member);
}

package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.CartItem;
import com.project.shoppingmall.repository.custom.CartItemRepositoryCustom;

public interface CartItemRepository extends JpaQueryDslPredicateRepository<CartItem, Long>, CartItemRepositoryCustom {
}

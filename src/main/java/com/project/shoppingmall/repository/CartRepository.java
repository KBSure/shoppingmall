package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Cart;
import com.project.shoppingmall.repository.custom.CartRepositoryCustom;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaQueryDslPredicateRepository<Cart, Long>, CartRepositoryCustom {
}

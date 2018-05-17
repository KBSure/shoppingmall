package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.repository.custom.CartRepositoryCustom;

public interface CartRepository extends JpaQueryDslPredicateRepository, CartRepositoryCustom {
}

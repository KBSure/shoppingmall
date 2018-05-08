package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Product;

public interface ProductRepository extends JpaQueryDslPredicateRepository<Product, Long> {
}

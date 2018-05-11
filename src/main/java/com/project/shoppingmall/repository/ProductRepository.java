package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.repository.custom.ProductRepositoryCustom;

public interface ProductRepository extends JpaQueryDslPredicateRepository<Product, Long>, ProductRepositoryCustom {
}

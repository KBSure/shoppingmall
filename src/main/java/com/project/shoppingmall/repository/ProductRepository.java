package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.repository.custom.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaQueryDslPredicateRepository<Product, Long>, ProductRepositoryCustom {
    
    @Modifying
    @Query("update Product p set p.quantity = p.quantity-:quantity where p.id = :productId")
    int minusProductQuantity(@Param(value = "productId") Long productId, @Param(value = "quantity") int quantity);

}

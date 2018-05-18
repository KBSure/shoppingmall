package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.OrderInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {
    Page<Product> findAllProducts(String searchStr, String prdCate, Pageable pageable);

    List<Product> findBestSellerProductsWithLimit();

    List<Product> findSoldOutProducts(List<Long> productIds);
    
    long addProductQuantity(Long productId, int quantity);
}

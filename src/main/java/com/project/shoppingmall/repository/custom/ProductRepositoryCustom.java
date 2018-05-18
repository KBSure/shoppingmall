package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {
    Page<Product> findAllProducts(String searchStr, String prdCate, Pageable pageable);

    List<Product> findBestSellerProductsWithLimit();

//    List<Product> findAllProductsWithThumnailByProductIds(List<Long> productIds);
}

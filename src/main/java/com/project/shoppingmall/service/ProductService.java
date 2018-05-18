package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    
    List<Product> getBestSellers();
    
    Page<Product> getProducts(String searchStr, String prdCate, int page, String sortType);
    
    Product getProduct(Long id);

    List<Product> getProducts(List<Long> productIds);

    List<Product> getSoldOutProducts(List<Long> productIds);
    
}

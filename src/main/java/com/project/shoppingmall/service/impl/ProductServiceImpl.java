package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.repository.ProductRepository;
import com.project.shoppingmall.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private EntityManager entityManager;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Product> getBestSellers() {
        
        return productRepository.findBestSellerProductsWithLimit();
    }
    
    @Override
    public Page<Product> getProducts(String searchStr, String prdCate, int page, String sortType) { // 카테고리, 현재페이지숫자,
        
        Sort sort = createSort(sortType);
        
        Pageable pageable = PageRequest.of(page - 1, 8, sort);
        
        return productRepository.findAllProducts(searchStr, prdCate, pageable);
    }
    
    @Override
    public Product getProduct(Long id) {

        return productRepository.findById(id).get();
    }
    
    private Sort createSort(String sortType) {
    
        if(StringUtils.isBlank(sortType)) {
            return Sort.by(Sort.Direction.DESC, "id");
        }
    
        String upperCase = sortType.toUpperCase();
        
        switch (SortType.valueOf(upperCase)) {
            case NEWEST:
                return Sort.by(Sort.Direction.DESC, "id");
            case PRICE_ASC:
                return Sort.by(Sort.Direction.ASC, "price");
            case PRICE_DESC:
                return Sort.by(Sort.Direction.DESC, "price");
            default:
                throw new IllegalArgumentException("잘못된 정렬 조건 입니다.");
        }
    }

    @Override
    public List<Product> getProducts(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }
    
    private enum SortType {
        NEWEST, PRICE_ASC, PRICE_DESC;
    }
    
    @Override
    public List<Product> getSoldOutProducts(List<Long> productIds) {
        
        return productRepository.findSoldOutProducts(productIds);
    }
    
    @Transactional
    @Override
    public List<Product> minusProductsQuantity(List<Long> productIds, List<Integer> quantities) {
    
        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            int quantity = quantities.get(i);
            productRepository.minusProductQuantity(productId, quantity);
        }
        
        List<Product> products = productRepository.findAllById(productIds);
        
        boolean minusQuantity = false;
        StringBuilder sb = new StringBuilder();
        for(Product product : products) {
            if(product.getQuantity() < 0) {
                minusQuantity = true;
                sb.append(product.getName()+",");
            }
        }
        
        if(minusQuantity) {
            String productNames = sb.deleteCharAt(sb.length() - 1).toString();
            throw new IllegalStateException(productNames);
        }
    
        return products;
    }
}

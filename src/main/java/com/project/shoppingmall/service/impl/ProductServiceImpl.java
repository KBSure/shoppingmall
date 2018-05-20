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

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
    
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
    public List<Product> getProducts(List<Long> prdIdList) {
        return productRepository.findAllById(prdIdList);
    }

    private enum SortType {
        NEWEST, PRICE_ASC, PRICE_DESC;
    }
    
    @Override
    public List<Product> getAllProductsWithThumnail(List<Long> productIds) {
//        return productRepository.findAllProductsWithThumnailByProductIds(productIds);
        return null;
    }
}

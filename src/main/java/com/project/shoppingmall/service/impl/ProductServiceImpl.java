package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.repository.ProductRepository;
import com.project.shoppingmall.service.ProductService;
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
        return productRepository.findBestSellerProductsByLimit();
    }
    
    @Override
    public Page<Product> getProducts(String searchStr, String prdCate, int page, String sortType) {
        
        //TODO 정렬 유형에 따른 소트 생성해야함.
        Sort defaultSort = Sort.by(Sort.Direction.DESC, "id");
        
        Pageable pageable = PageRequest.of(page - 1, 8, defaultSort);
        
        return productRepository.findAllProducts(searchStr, prdCate, pageable);
    }
}

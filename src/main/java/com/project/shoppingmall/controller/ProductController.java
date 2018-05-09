package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    // 상품리스트
    @GetMapping
    public String products(@RequestParam(name = "prd_cate", required = false)String prdCate
            , @RequestParam(name="page", defaultValue = "1") int page) {
        
        // 상품 목록 조회(검색조건 유, 검색조건 무, 정렬조건)
        // 검색 조건 없으면 전체 상품 조회.
        // 정렬 조건 없으면, 최신순 조회
        
        // 조회한 상품 목록 모델에 담기
        // 기타 파라미터 모델에 담기
        
        return "products/product_list";
    }
    
    // 상품 상세페이지
    @GetMapping("/{id}")
    public String productDetail(@PathVariable("id") Long id, @RequestParam(name = "prd_cate", required = false)String prdCate
            , @RequestParam(name="page", defaultValue = "1") int page) {
        
        // id 로 상품 조회하기
        // 조회한 상품 모델에 담기
        
        return "products/product_detail";
    }
}

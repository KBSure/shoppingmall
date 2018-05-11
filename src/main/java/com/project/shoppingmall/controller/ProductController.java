package com.project.shoppingmall.controller;

import com.project.shoppingmall.common.Pagination;
import com.project.shoppingmall.domain.Category;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.service.CategoryService;
import com.project.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryService categoryService;
    
    @ModelAttribute("categories")
    public List<Category> getCategories() {
    
        return categoryService.getCategories();
    }
    
    // 상품리스트
    @GetMapping
    public String products(@RequestParam(name = "prd_cate", required = false)String prdCate
            , @RequestParam(name="sort_type", required = false) String sortType, @RequestParam(defaultValue = "1") int page
            , @RequestParam(name="search_str", required = false) String searchStr, Model model) {
        
        // 상품 목록 조회(검색조건 유, 검색조건 무, 정렬조건, 카테고리)
        Page<Product> productPage = productService.getProducts(searchStr, prdCate, page, sortType);
        
        // 조회한 상품 목록 모델에 담기
        List<Product> products = productPage.getContent();
        Pagination pagination = new Pagination(productPage.getTotalElements(), productPage.getSize(), page, 5);
        
        model.addAttribute("products", products);
        model.addAttribute("pagination", pagination);
        // 기타 파라미터 모델에 담기
        model.addAttribute("page", page);
        model.addAttribute("searchStr", searchStr);
        model.addAttribute("prdCate", prdCate);
        model.addAttribute("sortType", sortType);
        
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

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
    public String products(@RequestParam(name = "prd_cate")String prdCate, @RequestParam(name="page")int page) {
        return "products/product_list";
    }
    
    // 상품 상세페이지
    @GetMapping("/{id}")
    public String productDetail(@PathVariable("id") Long id, @RequestParam(name = "prd_cate")String prdCate, @RequestParam(name="page")int page){
        return "products/product_detail";
    }
}

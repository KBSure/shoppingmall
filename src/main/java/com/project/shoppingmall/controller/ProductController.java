package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("products")
public class ProductController {

    // 상품리스트
    @GetMapping
    public String products() {
    
        return "product/product_list";
    }
    
    // 상품 상세페이지
    @GetMapping("/{id}")
    public String productDetail(@PathVariable Long id) {
        
        return "product/product_detail";
    }
    
    // 상품 QnA 리스트
    @GetMapping("/{id}/qna")
    @ResponseBody
    public List productQnas() {
        //TODO 엔티티 구성되면, 반환값 변경 필요.
        return null;
    }
    
    // 상품 QnA 등록
    @PostMapping("/{id}/qna")
    public List registProductQna() {
        //TODO 엔티티 구성되면, 반환값 변경 필요.
        return null;
    }
    
    // 상품 QnA 상세내용
    @GetMapping("/{id}/qna/{qnaid}")
    @ResponseBody
    public Object productQna() {
        //TODO 엔티티 구성되면, 반환값 변경 필요.
        return null;
    }
    
    @DeleteMapping("/{id}/qna/{qnaid}")
    public List deleteProductQna() {
        //TODO 엔티티 구성되면, 반환값 변경 필요.
        return null;
    }
    
}

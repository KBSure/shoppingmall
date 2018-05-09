package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String main(Model model) {
        
        // 베스트 셀러 상품 목록 조회
        // 상품 목록 Model 에 담기
        
        return "main/main";
    }
}

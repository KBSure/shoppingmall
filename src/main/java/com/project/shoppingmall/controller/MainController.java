package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {
    
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String main(Model model) {
        
        List<Product> bestSellers = productService.getBestSellers();
        model.addAttribute("bestSellers", bestSellers);
        return "main/main";
    }
}

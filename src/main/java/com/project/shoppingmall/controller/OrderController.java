package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    //장바구니
    //주문페이지
    //주문
    //주문완료 페이지

    @GetMapping("/cart")
    public String cart(){
        return "/order/cart";
    }

    @GetMapping("/order")
    public String order(){
        return "order/order";
    }

    @PostMapping("/order")
    public String ordering(){
        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success(){
        return "order/success";
    }
}

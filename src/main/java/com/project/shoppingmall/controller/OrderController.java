package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    //장바구니
    @GetMapping("/cart")
    public String cart(@RequestParam(name = "prd_cate")String prdCate, @RequestParam(name = "page")int page,
                       @RequestParam(name = "prd_id")Long prdId, @RequestParam(name = "prd_cnt")int prdCnt){
        return "/order/cart";
    }

    //주문페이지
    @GetMapping("/order")
    public String orderForm(@RequestParam(name = "prd_id")Long prdId, @RequestParam(name = "prd_cnt")int prdCnt){
        return "order/order";
    }

    //주문
    @PostMapping("/order")
    public String order(){
        Long id = 0l;
        // /order/{id}/success
        return "redirect:/order/" + id + "/success";
    }

    //주문완료 페이지
    @GetMapping("/{id}/success")
    public String success(@PathVariable("id") Long id){
        return "order/success";
    }
}

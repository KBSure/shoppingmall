package com.project.shoppingmall.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/order")
public class OrderController {
    //장바구니
    @GetMapping("/cart")
    public String cart(){
        return "/order/cart";
    }
    
    @PostMapping("/cart")
    @ResponseBody
    public ResponseEntity<Void> registCart() {
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/wishlist")
    @ResponseBody
    public ResponseEntity<String> registWishlist(HttpServletResponse response) {
    
        return new ResponseEntity<>(HttpStatus.OK);
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

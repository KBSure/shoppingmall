package com.project.shoppingmall.controller.api;

import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.security.LoginMember;
import com.project.shoppingmall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderApiController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/cart")
    public ResponseEntity<Void> registCart(@Validated @RequestBody CartInfo cartInfo, HttpSession session, Principal principal){
        
        @SuppressWarnings("unchecked")
        List<CartInfo> cartList = (List<CartInfo>) session.getAttribute("cartList");
        if(cartList == null) {
            cartList = new ArrayList<>();
        }
        cartList.add(cartInfo);
        session.setAttribute("cartList", cartList);
        
        if(principal != null) {
            LoginMember loginMember = (LoginMember) principal;
            orderService.registCart(loginMember.getId(), cartInfo.getPrdId());
        }
        
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

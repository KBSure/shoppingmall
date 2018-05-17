package com.project.shoppingmall.controller.api;

import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.security.LoginMember;
import com.project.shoppingmall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderApiController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/cart")
    public ResponseEntity<List<CartInfo>> registCart(@RequestBody CartInfo cartInfo, HttpSession session, Authentication authentication){
        
        @SuppressWarnings("unchecked")
        List<CartInfo> cartList = (List<CartInfo>) session.getAttribute("cartItems");
        if(cartList == null) {
            cartList = new ArrayList<>();
        }
        
        if(authentication != null) {
            LoginMember loginMember = (LoginMember) authentication.getPrincipal();
//            orderService.registCart(loginMember.getId(), cartInfo);
        }
        
        boolean exist = false;
        for (CartInfo info : cartList) {
            if(info.getPrdId().equals(cartInfo.getPrdId())) {
                info.setQuantity(info.getQuantity() + cartInfo.getQuantity());
                exist = true;
            }
        }
        
        if(!exist) {
            cartList.add(cartInfo);
        }
    
        session.setAttribute("cartList", cartList);
        
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }
    
    @DeleteMapping("/cart")
    public ResponseEntity<List<CartInfo>> removeCarts(@RequestBody List<CartInfo> cartInfos, Authentication authentication) {
    
        if(authentication != null) {
    
            LoginMember loginMember = (LoginMember) authentication.getPrincipal();
            List<Long> productIds = cartInfos.stream().map(c -> c.getPrdId()).collect(Collectors.toList());
            
            orderService.removeCarts(loginMember.getId(), productIds);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

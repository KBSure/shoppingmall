package com.project.shoppingmall.controller.api;

import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.security.LoginMember;
import com.project.shoppingmall.service.CartService;
import com.project.shoppingmall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderApiController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CartService cartService;
    
    @PostMapping("/cart")
    public ResponseEntity<List<CartInfo>> registCart(@RequestBody List<CartInfo> cartInfos, HttpSession session, Authentication authentication){
        
        @SuppressWarnings("unchecked")
        Map<Long, CartInfo> cartInfoMap = (Map<Long, CartInfo>) session.getAttribute("cartInfoMap");
        if(cartInfoMap == null) {
            cartInfoMap = new LinkedHashMap<>();
        }
        
        // 로그인 사용자면 사용자 저장
        if(authentication != null) {
            LoginMember loginMember = (LoginMember) authentication.getPrincipal();
//            orderService.registCart(loginMember.getId(), cartInfo);
        }
        
        // 기존 세션에 중복 정보 있으면 수량 업데이트하고, 없으면 추가.
        for (CartInfo cartInfo : cartInfos) {
            updateCartInfoMap(cartInfoMap, cartInfo);
        }

        session.setAttribute("cartInfoMap", cartInfoMap);
        
        List<CartInfo> cartInfoList = new ArrayList<>(cartInfoMap.values());
        
        return new ResponseEntity<>(cartInfoList, HttpStatus.OK);
    }
    
    private void updateCartInfoMap(Map<Long, CartInfo> cartInfoMap, CartInfo cartInfo) {
        cartInfoMap.compute(cartInfo.getPrdId(), (prdId, info) -> {
            
            if(info == null) {
               return cartInfo;
            }
           
            info.updateQuantity(cartInfo.getQuantity());
            return info;
        });
    }
    
    @DeleteMapping("/cart")
    public ResponseEntity<List<CartInfo>> removeCarts(@RequestBody List<CartInfo> cartInfos, Authentication authentication) {
    
        if(authentication != null) {
    
            LoginMember loginMember = (LoginMember) authentication.getPrincipal();
            List<Long> productIds = cartInfos.stream().map(c -> c.getPrdId()).collect(Collectors.toList());
            
//            orderService.removeCarts(loginMember.getId(), productIds);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

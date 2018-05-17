package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.CartItem;
import com.project.shoppingmall.dto.CartInfo;

import java.util.List;
import java.util.Map;

public interface CartService {
    
    List<CartItem> getMemberCart(Long memberId);

//    List<CartItem> getAllCartsByCartIds(List<Long> cartIds);
    
    List<CartItem> registCart(Long memberId, List<CartInfo> cartInfos);
    
    void modifyCart(Long memberId, List<CartInfo> cartInfos);
    
    void removeCart(Long memberId, List<Long> productIds);
    
}

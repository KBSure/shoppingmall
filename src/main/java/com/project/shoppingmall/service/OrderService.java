package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Cart;
import com.project.shoppingmall.dto.CartInfo;

import java.util.List;

public interface OrderService {
    
    List<Cart> getAllMemebrCarts(Long memberId);
    
    List<Cart> getAllCartsByCartIds(List<Long> cartIds);
    
    Cart registCart(Long memberId, Long productId);
    
    void modifyCart(Long memberId, CartInfo cartInfo);
    
    void modifyCarts(Long memberId, List<CartInfo> cartInfos);
    
    void removeCarts(List<Long> cartIds);
}

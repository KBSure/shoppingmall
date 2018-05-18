package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.CartItem;

import java.util.List;

public interface CartItemRepositoryCustom {
    
    List<CartItem> findCartItems(Long memberId);

    List<CartItem> findCartItems(Long memberId, List<Long> productIds);

}

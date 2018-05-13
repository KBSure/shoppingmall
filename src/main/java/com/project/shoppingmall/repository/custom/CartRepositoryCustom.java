package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.Cart;

import java.util.List;

public interface CartRepositoryCustom {
    
    List<Cart> findAllMemberCarts(Long memberId);
    
}

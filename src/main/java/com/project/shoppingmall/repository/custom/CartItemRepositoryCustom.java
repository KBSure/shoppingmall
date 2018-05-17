package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.CartItem;

import java.util.List;

public interface CartItemRepositoryCustom {
    
    List<CartItem> findCartItemsByMemberId(Long memberId);

//    List<Cart> findAllMemberCartsByProductIds(Long memberId, List<Long> productIds);

}

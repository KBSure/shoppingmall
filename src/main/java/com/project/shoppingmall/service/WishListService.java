package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.WishItem;

import java.util.List;


public interface WishListService {

    List<WishItem> getWishList(String email);
    List<WishItem> registWishlist(Member member, List<Product> productList);
    void deleteWishList(List<Long> wishItemIdList);
}

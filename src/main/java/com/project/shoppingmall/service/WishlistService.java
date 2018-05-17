package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;

import java.util.List;


public interface WishlistService {

//    List<WishItem> getWishlists(String email);
//    List<WishItem> addWishlist(List<Product> productList, Member member);
    void deleteWishlist(List<Long> wishlistIdList);
}

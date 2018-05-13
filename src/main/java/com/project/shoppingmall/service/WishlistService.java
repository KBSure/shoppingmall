package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Wishlist;

import java.util.List;


public interface WishlistService {

    List<Wishlist> getWishlist();
    List<Wishlist> getWishlistByMember();
    Wishlist addWishlist(Wishlist wishlist);
}

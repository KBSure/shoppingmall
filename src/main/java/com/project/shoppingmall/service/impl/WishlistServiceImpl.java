package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Wishlist;
import com.project.shoppingmall.service.WishlistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Override
    @Transactional(readOnly = true)
    public List<Wishlist> getWishlist() {
        return null;
    }

    @Override
    public List<Wishlist> getWishlistByMember() {
        return null;
    }

    @Override
    public Wishlist addWishlist(Wishlist wishlist) {
        return null;
    }
}

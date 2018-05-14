package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.Wishlist;
import com.project.shoppingmall.repository.WishlistRepository;
import com.project.shoppingmall.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Wishlist> getWishlists(String email) {
        return wishlistRepository.findAllByMemberEmail(email);
    }

    @Override
    @Transactional
    public List<Wishlist> addWishlist(List<Product> productList, Member member) {
//        productList랑 member wishlist에 set해서 wishlist를 추가한다.

        List<Wishlist> wishlistList = new ArrayList<>();
        for (Product product : productList){
            Wishlist wishlist = new Wishlist();
            wishlist.setProduct(product);
            wishlist.setMember(member);
            wishlistList.add(wishlist);
        }

        List<Wishlist> saveWishlistList = wishlistRepository.saveAll(wishlistList);
        return saveWishlistList;
    }

    @Override
    @Transactional
    public void deleteWishlist(List<Long> wishlistIdList) {
        for (Long id : wishlistIdList) {
            wishlistRepository.deleteById(id);
        }
        return;
    }
}

package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.WishItem;
import com.project.shoppingmall.repository.WishItemRepository;
import com.project.shoppingmall.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListServiceImpl implements WishListService {

    @Autowired
    WishItemRepository wishItemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<WishItem> getWishList(String email) {
        return wishItemRepository.findAllByMemberEmail(email);
    }

    @Override
    @Transactional
    public void deleteWishList(List<Long> wishItemIdList) {
        for (Long id : wishItemIdList) {
            wishItemRepository.deleteById(id);
        }
    }
    
    @Transactional
    @Override
    public List<WishItem> registWishlist(Member member, List<Product> productList) {
        List<WishItem> wishItems = productList.stream().map(product -> makeWishItem(member, product)).collect(Collectors.toList());
    
        return wishItemRepository.saveAll(wishItems);
    }
    
    private WishItem makeWishItem(Member member, Product product) {
        WishItem wishItem = new WishItem();
        wishItem.setMember(member);
        wishItem.setProduct(product);
        return wishItem;
    }
}

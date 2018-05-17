package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.CartItem;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.repository.CartItemRepository;
import com.project.shoppingmall.repository.MembersRepository;
import com.project.shoppingmall.repository.ProductRepository;
import com.project.shoppingmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static java.util.stream.Collectors.*;

@Transactional(readOnly = true)
@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private MembersRepository membersRepository;
    
    @Override
    public List<CartItem> getMemberCart(Long memberId) {
        return null;
    }
    
    @Transactional
    @Override
    public List<CartItem> registCart(Long memberId, List<CartInfo> cartInfos) {
    
    
        final Map<Long, CartInfo> cartInfoMap = cartInfos.stream()
                                                        .collect(toMap(CartInfo::getPrdId, c -> c));
    
        // 멤머가 가진 카트 아이템 조회
        List<CartItem> memberCartItems = cartItemRepository.findCartItemsByMemberId(memberId);
        // 중복값이 존재하면, 기존 수량 변경
        for (CartItem cartItem : memberCartItems) {
            Long productId = cartItem.getProduct().getId();
            if(cartInfoMap.containsKey(productId)) {
                int quantity = cartInfoMap.get(productId).getQuantity();
                cartItem.updateQuantity(quantity);
                cartInfoMap.remove(productId);
            }
        }
    
        Member member = membersRepository.findById(memberId).get();
        
        // 중복되지 않는 상품 조회
        List<Product> products = productRepository.findAllById(cartInfoMap.keySet());
    
        // 중복되지 않는값 카트아이템에 저장
        List<CartItem> cartItems = new ArrayList<>();
        for (Product product : products) {
            CartItem cartItem = new CartItem();
            cartItem.setMember(member);
            cartItem.setProduct(product);
            int quantity = cartInfoMap.get(product.getId()).getQuantity();
            cartItem.setQuantity(quantity);
            cartItems.add(cartItem);
        }
    
        return cartItemRepository.saveAll(cartItems);
    }
    
    @Override
    public void modifyCart(Long memberId, List<CartInfo> cartInfos) {
    
    }
    
    @Override
    public void removeCart(Long memberId, List<Long> productIds) {
    
    }
}

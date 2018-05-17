package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.CartItem;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.repository.CartItemRepository;
import com.project.shoppingmall.repository.ProductRepository;
import com.project.shoppingmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional(readOnly = true)
@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<CartItem> getMemberCart(Long memberId) {
        return null;
    }
    
    @Transactional
    @Override
    public List<CartItem> registCart(Long memberId, Map<Long, CartInfo> cartInfoMap) {
        
        
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
    
        Member member = memberCartItems.get(0).getMember();
        
        // 중복되지 않는 상품 조회
        List<Product> products = productRepository.findAllById(cartInfoMap.keySet());
    
        // 중복되지 않는값 카트아이템에 저장
        List<CartItem> cartItems = new ArrayList<>();
        // TODO 중복안되는 상품으로 CartItem 만들고 디비에 저장해야댐.
        
        
        return null;
    }
    
    @Override
    public void modifyCart(Long memberId, Map<Long, CartInfo> cartInfoMap) {
    
    }
    
    @Override
    public void removeCart(Long memberId, List<Long> productIds) {
    
    }
}

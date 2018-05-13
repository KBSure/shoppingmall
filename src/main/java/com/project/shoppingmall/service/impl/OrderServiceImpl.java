package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Cart;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.repository.CartRepository;
import com.project.shoppingmall.repository.MembersRepository;
import com.project.shoppingmall.repository.ProductRepository;
import com.project.shoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private MembersRepository membersRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public List<Cart> getAllMemebrCarts(Long memberId) {
        
        return cartRepository.findAllMemberCarts(memberId);
    }
    
    @Transactional
    @Override
    public Cart registCart(Long memberId, Long productId) {
    
        Member member = membersRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 아이디 입니다."));
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setMember(member);
        
        return cartRepository.save(cart);
    }
    
    @Transactional
    @Override
    public void modifyCart(Long memberId, CartInfo cartInfo) {
        
        // 장바구니 조회
        
        // 카트 수량 변경
        
        // 디비에 반영.
        
        
    }
    
    @Override
    public void modifyCarts(Long memberId, List<CartInfo> cartInfos) {
        
        // 멤버가 가진 장바구니 전체 조회
        
        // 멤버가 가진 장바구니 내역중에서 상품 아이디 일치하는 상품 수량 변경.
        
        // 디비에 반영
        
    }
    
    @Transactional
    @Override
    public void removeCart(Long memberId, Long productId) {
        // 멤버아이디와 상품아이디가 동일한 카트 삭제.
    }
    
    @Override
    public void removeCarts(Long memberId, List<CartInfo> cartInfos) {
        // cartInfos에서 상품 아이디만 추출
        
        // 멤버가 가진 카트중에서 일치하는 상품 아이디만 삭제.
        
    }
}

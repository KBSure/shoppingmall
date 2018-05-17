package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.repository.CartItemRepository;
import com.project.shoppingmall.repository.MembersRepository;
import com.project.shoppingmall.repository.ProductRepository;
import com.project.shoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private MembersRepository membersRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
//    @Override
//    public List<CartItem> getAllMemebrCarts(Long memberId) {
//
//        return cartItemRepository.findAllMemberCarts(memberId);
//    }
//
//    @Override
//    public List<Cart> getAllCartsByCartIds(List<Long> cartIds) {
//        return cartItemRepository.findAllById(cartIds);
//    }
    
//    @Transactional
//    @Override
//    public Cart registCart(Long memberId, CartInfo cartInfo) {
//
//        Member member = membersRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 아이디 입니다."));
//        Product product = productRepository.findById(cartInfo.getPrdId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
//
//        Optional<Cart> memberCart = getAllMemebrCarts(member.getId())
//                .stream().filter(c -> c.getProduct().getId().equals(product.getId()))
//                .reduce((c1, c2) -> c1);
//
//        if(memberCart.isPresent()) {
//            Cart cart = memberCart.get();
//            cart.setQuantity(cart.getQuantity() + cartInfo.getQuantity());
//            return cart;
//        }
//
//        Cart cart = new Cart();
//        cart.setProduct(product);
//        cart.setMember(member);
//
//        return cartItemRepository.save(cart);
//        return null;
//    }

}

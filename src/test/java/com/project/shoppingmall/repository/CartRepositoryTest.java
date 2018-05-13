package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Cart;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CartRepositoryTest {
    
    @Autowired
    private CartRepository cartRepository;
    
    @Autowired
    private MembersRepository membersRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private EntityManager entityManager;
    
    @Test
    public void testNotNull() {
        assertNotNull(cartRepository);
        assertNotNull(entityManager);
    }

    @Test
    public void testSaveCart() {
        Member member = membersRepository.findById(1L).get();
        Product product = productRepository.findById(1L).get();
    
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setMember(member);
    
        Cart saveCart = cartRepository.save(cart);
    
        Cart findCart = cartRepository.findById(saveCart.getId()).get();
        
        assertEquals(saveCart, findCart);
    }
    
    @Test
    public void testfindAllCart() {
        Member member = membersRepository.findById(1L).get();
        List<Cart> cartList = new ArrayList<>();
        
        final long saveCount = 5;
        for(long i = 1; i <= saveCount; i++) {
            Product product = productRepository.findById(i).get();
            Cart cart = new Cart();
            cart.setMember(member);
            cart.setProduct(product);
            cartList.add(cart);
//            cartRepository.save(cart);
        }
        cartRepository.saveAll(cartList);
        
        entityManager.flush();
    
        List<Cart> memberCarts = cartRepository.findAllMemberCarts(member.getId());
        
        assertEquals(saveCount, memberCarts.size());
        
        memberCarts.forEach(c -> {
            Long cartMemberId = c.getMember().getId();
            assertTrue(Long.compare(cartMemberId, member.getId()) == 0);
        });
    }
    
}
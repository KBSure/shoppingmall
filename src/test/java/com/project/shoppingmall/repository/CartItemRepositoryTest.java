package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CartItemRepositoryTest {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private MembersRepository membersRepository;
    
    @Autowired
    private EntityManager entityManager;
    
    @Test
    public void testNotNull() {
        assertNotNull(cartItemRepository);
        assertNotNull(entityManager);
    }

    @Test
    public void testSaveCart() {
        Member member = membersRepository.findById(1L).get();
        
//        Cart cart = new Cart();
//        cart.setMember(member);
//
//        Cart saveCart = cartItemRepository.save(cart);
//
//        Cart findCart = cartItemRepository.findById(saveCart.getId()).get();
//
//        assertEquals(saveCart, findCart);
    }
    
    @Test
    public void testfindMembersCart() {
        Member member = membersRepository.findById(1L).get();
    
//        Cart cart = cartItemRepository.findCartByMemberId(member.getId());
//
//        assertTrue(cart.getCartItems().size() > 1);
//
//        for (CartItem cartItem : cart.getCartItems()) {
//            System.out.println(cartItem.getId());
//        }
    }
    
    
    @Test
    public void testFindAllMemberCartsByProductIds() {
        
    
    }
    
    @Test
    public void testDeleteCarts() {
//        cartItemRepository.deleteAllByIds(Arrays.asList(1L));
    }
}
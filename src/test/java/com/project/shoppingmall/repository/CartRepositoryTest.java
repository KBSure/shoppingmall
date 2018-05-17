package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    private EntityManager entityManager;
    
    @Test
    public void testNotNull() {
        assertNotNull(cartRepository);
        assertNotNull(entityManager);
    }

    @Test
    public void testSaveCart() {
        Member member = membersRepository.findById(1L).get();
        
//        Cart cart = new Cart();
//        cart.setMember(member);
//
//        Cart saveCart = cartRepository.save(cart);
//
//        Cart findCart = cartRepository.findById(saveCart.getId()).get();
//
//        assertEquals(saveCart, findCart);
    }
    
    @Test
    public void testfindMembersCart() {
        Member member = membersRepository.findById(1L).get();
    
//        Cart cart = cartRepository.findCartByMemberId(member.getId());
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
//        cartRepository.deleteAllByIds(Arrays.asList(1L));
    }
}
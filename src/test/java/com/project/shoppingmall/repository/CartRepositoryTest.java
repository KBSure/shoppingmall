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
    public void testfindAllMembersCart() {
        final long saveCount = 5;
    
        List<Cart> saveCartList = saveTestCarts(saveCount);
        entityManager.flush();
        Member member = saveCartList.get(0).getMember();
    
        List<Cart> memberCarts = cartRepository.findAllMemberCarts(member.getId());
        
        assertEquals(saveCount, memberCarts.size());
        
        memberCarts.forEach(c -> {
            Long cartMemberId = c.getMember().getId();
            assertTrue(Long.compare(cartMemberId, member.getId()) == 0);
        });
    }
 
    @Test
    public void testDeleteAllCarts() {
        List<Cart> saveTestCarts = saveTestCarts(5);

        Member member = saveTestCarts.get(0).getMember();
        Long memberId = member.getId();
        
        List<Cart> findCarts = cartRepository.findAllMemberCarts(memberId);
        cartRepository.deleteInBatch(findCarts);
        entityManager.flush();
    
        List<Cart> afterCarts = cartRepository.findAllMemberCarts(memberId);
        
        assertTrue(afterCarts.isEmpty());
    }
    
    @Transactional
    public List<Cart> saveTestCarts(long saveCount) {
        Member newMember = new Member();
        Role role = new Role();
        role.setName("USER");
        newMember.addRole(role);
        membersRepository.save(newMember);
    
        Member member = membersRepository.findById(newMember.getId()).get();
        List<Cart> cartList = new ArrayList<>();
    
        for(long i = 1; i <= saveCount; i++) {
            Product product = productRepository.findById(i).get();
            Cart cart = new Cart();
            cart.setMember(member);
            cart.setProduct(product);
            cartList.add(cart);
        }
        List<Cart> saveList = cartRepository.saveAll(cartList);
    
        return saveList;
    }
    
    @Test
    public void testDeleteCarts() {
//        cartRepository.deleteAllByIds(Arrays.asList(1L));
    }
}
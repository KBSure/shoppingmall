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
import java.util.List;

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
    private ProductRepository productRepository;
    
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
        Product product = productRepository.findById(1L).get();
    
        CartItem cart = new CartItem();
//        cart.setProduct(product);
        cart.setMember(member);
    
        CartItem saveCart = cartItemRepository.save(cart);
    
        CartItem findCart = cartItemRepository.findById(saveCart.getId()).get();
        
        assertEquals(saveCart, findCart);
    }
    
    @Test
    public void testfindAllMembersCart() {
        final long saveCount = 5;
    
        List<CartItem> saveCartList = saveTestCarts(saveCount);
        entityManager.flush();
        Member member = saveCartList.get(0).getMember();
    
//        List<CartItem> memberCarts = cartItemRepository.findAllMemberCarts(member.getId());
//
//        assertEquals(saveCount, memberCarts.size());
//
//        memberCarts.forEach(c -> {
//            Long cartMemberId = c.getMember().getId();
//            assertTrue(Long.compare(cartMemberId, member.getId()) == 0);
//        });
    }
 
    @Test
    public void testDeleteAllCarts() {
        List<CartItem> saveTestCarts = saveTestCarts(5);

        Member member = saveTestCarts.get(0).getMember();
        Long memberId = member.getId();
        
//        List<CartItem> findCarts = cartItemRepository.findAllMemberCarts(memberId);
//        cartItemRepository.deleteInBatch(findCarts);
//        entityManager.flush();
//
//        List<CartItem> afterCarts = cartItemRepository.findAllMemberCarts(memberId);
//
//        assertTrue(afterCarts.isEmpty());
    }
    
    @Transactional
    public List<CartItem> saveTestCarts(long saveCount) {
        Member newMember = new Member();
        Role role = new Role();
        role.setName("USER");
        newMember.addRole(role);
        membersRepository.save(newMember);
    
        Member member = membersRepository.findById(newMember.getId()).get();
        List<CartItem> cartList = new ArrayList<>();
    
        for(long i = 1; i <= saveCount; i++) {
            Product product = productRepository.findById(i).get();
            CartItem cart = new CartItem();
            cart.setMember(member);
//            cart.setProduct(product);
            cartList.add(cart);
        }
        List<CartItem> saveList = cartItemRepository.saveAll(cartList);
    
        return saveList;
    }
    
    @Test
    public void testFindAllMemberCartsByProductIds() {
        
        List<CartItem> saveTestCarts = saveTestCarts(5);
    
        Member member = saveTestCarts.get(0).getMember();
    
//        List<Long> productIds = saveTestCarts.stream().map(c -> c.getProduct().getId()).collect(Collectors.toList());
    
//        List<Cart> findCarts = cartItemRepository.findAllMemberCartsByProductIds(member.getId(), productIds);
        
//        assertEquals(saveTestCarts.size(), findCarts.size());
//
//        for (Cart findCart : findCarts) {
//            assertEquals(member.getId(), findCart.getMember().getId());
//        }
    }
    
    @Test
    public void testDeleteCarts() {
//        cartItemRepository.deleteAllByIds(Arrays.asList(1L));
    }
}
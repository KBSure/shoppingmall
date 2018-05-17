package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public void testSaveCartItem() {
        Member member = membersRepository.findById(1L).get();
        Product product = productRepository.findById(1L).get();
    
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setMember(member);
    
        CartItem saveCart = cartItemRepository.save(cartItem);
    
        CartItem findCart = cartItemRepository.findById(saveCart.getId()).get();
        
        assertEquals(saveCart, findCart);
    }
    
    @Test
    public void testfindMemberCartItems() {
        final long saveCount = 5;
    
        List<CartItem> cartItems = saveTestCartItem(saveCount);
        entityManager.flush();
        Member member = cartItems.get(0).getMember();
    
        List<CartItem> memberCarts = cartItemRepository.findCartItems(member.getId());

        assertEquals(saveCount, memberCarts.size());

        memberCarts.forEach(c -> {
            Long cartMemberId = c.getMember().getId();
            assertTrue(Long.compare(cartMemberId, member.getId()) == 0);
        });
    }
 
    @Test
    public void testDeleteAllCarts() {
        List<CartItem> saveTestCarts = saveTestCartItem(5);

        Member member = saveTestCarts.get(0).getMember();
        Long memberId = member.getId();
        
        List<CartItem> findCarts = cartItemRepository.findCartItems(memberId);
        cartItemRepository.deleteInBatch(findCarts);
        entityManager.flush();

        List<CartItem> afterCarts = cartItemRepository.findCartItems(memberId);
        
        assertTrue(afterCarts.isEmpty());
    }
    
    private List<CartItem> saveTestCartItem(long saveCount) {
        Member newMember = new Member();
        Role role = new Role();
        role.setName("USER");
        newMember.addRole(role);
        membersRepository.save(newMember);
    
        Member member = membersRepository.findById(newMember.getId()).get();
        List<CartItem> cartList = new ArrayList<>();
    
        for(long i = 1; i <= saveCount; i++) {
            Product product = productRepository.findById(i).get();
            CartItem cartItem = new CartItem();
            cartItem.setMember(member);
            cartItem.setProduct(product);
            cartList.add(cartItem);
        }
        List<CartItem> saveList = cartItemRepository.saveAll(cartList);
    
        return saveList;
    }
    
    @Test
    public void testFindMemberCartByProductIds() {
        
        List<CartItem> saveTestCartItem = saveTestCartItem(5);
    
        Member member = saveTestCartItem.get(0).getMember();
    
        List<Long> productIds = saveTestCartItem.stream().map(c -> c.getProduct().getId()).collect(Collectors.toList());
    
        List<CartItem> cartItems = cartItemRepository.findCartItems(member.getId(), productIds);
        
        assertEquals(saveTestCartItem.size(), cartItems.size());

        for (CartItem cartItem : cartItems) {
            assertEquals(member.getId(), cartItem.getMember().getId());
        }
    }
    
    @Test
    public void testDeleteCarts() {
        List<CartItem> saveTestCartItem = saveTestCartItem(5);
        Member member = saveTestCartItem.get(0).getMember();
        List<Long> productIds = saveTestCartItem.stream().map(c -> c.getProduct().getId()).collect(Collectors.toList());
        List<CartItem> cartItems = cartItemRepository.findCartItems(member.getId(), productIds);
        
        cartItemRepository.deleteInBatch(cartItems);
        entityManager.flush();
    
        List<CartItem> deleteItems = cartItemRepository.findCartItems(member.getId(), productIds);
        assertTrue(deleteItems.isEmpty());
    }
}
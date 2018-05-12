package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.Wishlist;
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
public class WishlistRepositoryTest {
    
    @Autowired
    private WishlistRepository wishlistRepository;
    
    @Autowired
    private EntityManager entityManager;
    
    @Test
    public void testNotNull() {
        assertNotNull(wishlistRepository);
    }
    
    @Test
    public void testSave() {
        Product product = entityManager.getReference(Product.class, 1L);
        Member member = entityManager.getReference(Member.class, 1L);
        
        Wishlist wishlist = new Wishlist();
        wishlist.setProduct(product);
        wishlist.setMember(member);
    
        Wishlist save = wishlistRepository.save(wishlist);
        
        assertNotNull(save);
    }

}
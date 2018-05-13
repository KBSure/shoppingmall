package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Category;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.Wishlist;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
public class WishlistRepositoryTest {
    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void testNotNull(){
        assertNotNull(wishlistRepository);
        assertNotNull(entityManager);
    }

    @Test
    public void findWishlist(){
        List<Wishlist> list = wishlistRepository.findAll();
        System.out.println("========================================");
        for(Wishlist wishlist : list){
            System.out.println(wishlist.getId());
        }
    }


//    insert into product (id, name, shipping_charge, width, height, quantity, price, content, best_seller, reg_date, category_id) values (5, '(구)JS-S', 2500, 40, 55, 9, 1500, '짱 좋음', true, current_timestamp, 2);
    @Test
    public void saveWishlist(){
        Product product = new Product();


//        product.setName("(구)JS-S");
//        product.setShippingCharge(2500);
//        product.setWidth(40);
//        product.setHeight(55);
//        product.setQuantity(9);
//        product.setPrice(1500);
//        product.setContent("짱좋음");
//        product.setBestSeller(true);
//        product.setRegDate(LocalDateTime.now());
//        Category category = new Category();
//        category.setName("ya");
//        category.setProducts();
//        product.setCategory(category);
        Member member = new Member();

        Wishlist wishlist = new Wishlist();
        wishlist.setProduct(product);
        wishlist.setMember(member);
        Wishlist save = wishlistRepository.save(wishlist);
        System.out.println(save.getId());
        System.out.println(save.getMember().getId());


    }

    @Test
    public void getWishlists(){
        List<Wishlist> allByMemberEmail = wishlistRepository.findAllByMemberEmail("test2@gmail.com");

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");

        for (Wishlist wishlist : allByMemberEmail) {
            System.out.println(wishlist.getId());
        }
    }

    @Test
    public void getProductIds(){
        List<Product> productIdsByMemberEmail = wishlistRepository.findProductIdsByMemberEmail("test2@gmail.com");

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        for (Product product : productIdsByMemberEmail) {
            System.out.println(product.getName());
        }
    }
}
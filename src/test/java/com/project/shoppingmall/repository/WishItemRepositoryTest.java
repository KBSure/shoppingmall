package com.project.shoppingmall.repository;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.WishItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
public class WishItemRepositoryTest {
    @Autowired
    WishItemRepository wishItemRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    public void testNotNull(){
        assertNotNull(wishItemRepository);
        assertNotNull(entityManager);
    }

    @Test
    public void findWishlist(){
        List<WishItem> list = wishItemRepository.findAll();
        System.out.println("========================================");
        for(WishItem wishItem : list){
            System.out.println(wishItem.getId());
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

//        Wishlist wishlist = new Wishlist();
//        wishlist.setDetailProduct(product);
//        wishlist.setMember(member);
//        Wishlist save = wishItemRepository.save(wishlist);
//        System.out.println(save.getId());
//        System.out.println(save.getMember().getId());


    }

    @Test
    public void getWishlists(){
        List<WishItem> allByMemberEmail = wishItemRepository.findAllByMemberEmail("test2@gmail.com");

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");

        for (WishItem wishItem : allByMemberEmail) {
            System.out.println(wishItem.getId());
        }
    }

    @Test
    public void getProductIds(){
//        List<Product> productIdsByMemberEmail = wishItemRepository.findProductIdsByMemberEmail("test2@gmail.com");
//
//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        for (Product product : productIdsByMemberEmail) {
//            System.out.println(product.getName());
//        }
    }

    @Test
    public void deleteWishlist(){
//        List<Wishlist> prdIdList = new ArrayList<>();
//        Optional<Wishlist> byId = wishItemRepository.findById(4L);
//
//        wishItemRepository.delete(byId.get());
//
//        findWishlist();

//        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//        System.out.println(byId);
//        findWishlist();
//
        System.out.println("^^^^^^^^^^^^^^^^^^^");
        wishItemRepository.deleteById(5L);
        entityManager.flush();
        System.out.println("&&&&&&&&&&&&&&&&&&&&");
        findWishlist();

    }
}
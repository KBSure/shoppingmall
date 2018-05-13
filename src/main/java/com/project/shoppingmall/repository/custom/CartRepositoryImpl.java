package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class CartRepositoryImpl implements CartRepositoryCustom {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<Cart> findAllMemberCarts(Long memberId) {
        QCart cart = QCart.cart;
//        QProduct product = QProduct.product;
        QImage image = QImage.image;
        
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<Cart> cartJPAQuery = jpaQueryFactory.select(cart)
                                                        .from(cart)
                                                        .where(cart.member.id.eq(memberId));
    
        return cartJPAQuery.fetch();
    }
}

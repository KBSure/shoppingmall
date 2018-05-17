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
    
//    @Override
//    public Cart findCartByMemberId(Long memberId) {
//        QCart cart = QCart.cart;
//        QCartItem cartItem = QCartItem.cartItem;
//
//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
//        JPAQuery<Cart> cartJPAQuery = jpaQueryFactory.select(cart)
//                                                        .from(cart)
//                                                        .leftJoin(cart.cartItems, cartItem).fetchJoin()
//                                                        .where(cart.member.id.eq(memberId));
//
//        return null;
//    }
//
//    @Override
//    public List<Cart> findAllMemberCartsByProductIds(Long memberId, List<Long> productIds) {
//
//        QCart cart = QCart.cart;
//
//        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
//
//        return jpaQueryFactory.selectFrom(cart)
////                                .where(cart.member.id.eq(memberId).and(cart.product.id.in(productIds)))
//                                .fetch();
//    }
}

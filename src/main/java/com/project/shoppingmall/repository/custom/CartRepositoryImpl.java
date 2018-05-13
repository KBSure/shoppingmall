package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.Cart;
import com.project.shoppingmall.domain.QCart;
import com.project.shoppingmall.domain.QMember;
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
        
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        
        return jpaQueryFactory.selectFrom(cart).where(cart.member.id.eq(memberId)).fetch();
    }
}

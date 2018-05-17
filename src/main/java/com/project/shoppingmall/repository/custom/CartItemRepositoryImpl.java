package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.CartItem;
import com.project.shoppingmall.domain.QCartItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class CartItemRepositoryImpl implements CartItemRepositoryCustom {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<CartItem> findCartItemsByMemberId(Long memberId) {
    
        QCartItem cartItem = QCartItem.cartItem;
    
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<CartItem> jpaQuery = jpaQueryFactory.selectFrom(cartItem)
                                                    .innerJoin(cartItem.product).fetchJoin()
                                                    .where(cartItem.member.id.eq(memberId));
    
        return jpaQuery.fetch();
    }
}

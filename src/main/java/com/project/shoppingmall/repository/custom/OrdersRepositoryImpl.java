package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.Order;
import com.project.shoppingmall.domain.QOrder;
import com.project.shoppingmall.domain.QOrderImage;
import com.project.shoppingmall.domain.QOrderItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class OrdersRepositoryImpl implements OrdersRepositoryCustom {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<Order> findMemberOrders(Long memberId) {
    
        QOrder order = QOrder.order;
        
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
    
        JPAQuery<Order> jpaQuery = jpaQueryFactory.selectFrom(order).where(order.member.id.eq(memberId));
    
        return jpaQuery.fetch();
    }
    
}

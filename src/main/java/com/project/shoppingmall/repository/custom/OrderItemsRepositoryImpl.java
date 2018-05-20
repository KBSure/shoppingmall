package com.project.shoppingmall.repository.custom;

import com.project.shoppingmall.domain.OrderItem;
import com.project.shoppingmall.domain.QOrderItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class OrderItemsRepositoryImpl implements OrderItemsRepositoryCustom {
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public List<OrderItem> findAllOrderItems(Long orderId) {
    
        QOrderItem orderItem = QOrderItem.orderItem;
        
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery<OrderItem> jpaQuery = jpaQueryFactory.selectFrom(orderItem)
                                                        .leftJoin(orderItem.orderImages).fetchJoin()
                                                        .where(orderItem.order.id.eq(orderId));
    
        return jpaQuery.fetch();
    }
}

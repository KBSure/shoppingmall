package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.DeliveryState;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.project.shoppingmall.repository.custom.OrdersRepositoryCustom;
import java.util.List;
public interface OrdersRepository extends JpaQueryDslPredicateRepository<Order,Long>, OrdersRepositoryCustom {

    Page<Order> findAllByMember(Member member, Pageable pageable);
    Page<Order> findAllByMemberAndDeliveryState(Member member, DeliveryState deliveryState,Pageable pageable);
    List<Order> findAllByMember(Member member);

}

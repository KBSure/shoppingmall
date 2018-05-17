package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.WishItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishItemRepository extends JpaQueryDslPredicateRepository<WishItem, Long> {
    @Query("SELECT w FROM WishItem w WHERE w.member.email = :email")
    public List<WishItem> findAllByMemberEmail(@Param("email")String email);

//    @Query("SELECT w.product FROM Wishlist w WHERE w.member.email = :email")
//    public List<Product> findProductIdsByMemberEmail(@Param("email") String email);
}
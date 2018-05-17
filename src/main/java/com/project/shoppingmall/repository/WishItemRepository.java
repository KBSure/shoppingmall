package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.WishItem;

public interface WishItemRepository extends JpaQueryDslPredicateRepository<WishItem, Long> {
//    @Query("SELECT w FROM Wishlist w WHERE w.member.email = :email")
//    public List<Wishlist> findAllByMemberEmail(@Param("email")String email);
//
//    @Query("SELECT w.product FROM Wishlist w WHERE w.member.email = :email")
//    public List<Product> findProductIdsByMemberEmail(@Param("email") String email);
}
package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Wishlist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishlistRepository extends JpaQueryDslPredicateRepository<Wishlist, Long> {
    @Query("SELECT w FROM Wishlist w WHERE w.member.email = :email")
    public List<Wishlist> findAllByMemberEmail(@Param("email")String email);
}
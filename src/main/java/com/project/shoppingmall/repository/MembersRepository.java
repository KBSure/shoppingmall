package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Member;

public interface MembersRepository extends JpaQueryDslPredicateRepository<Member, Long> {
    public Member findMembersByEmail(String email);
}


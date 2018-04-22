package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Members;

public interface MembersRepository extends JpaQueryDslPredicateRepository<Members, Long> {
    public Members findMembersByEmail(String email);
}


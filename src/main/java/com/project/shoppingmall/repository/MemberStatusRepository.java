package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.MemberStat;
import com.project.shoppingmall.domain.MemberStatus;
public interface MemberStatusRepository extends JpaQueryDslPredicateRepository<MemberStatus,Long>  {
    MemberStatus findMemberStatusByStatus(MemberStat status);
}

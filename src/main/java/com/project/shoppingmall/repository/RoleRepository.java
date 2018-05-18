package com.project.shoppingmall.repository;

import com.project.shoppingmall.base.JpaQueryDslPredicateRepository;
import com.project.shoppingmall.domain.Role;
import com.project.shoppingmall.domain.RoleName;
//진행중
public interface RoleRepository extends JpaQueryDslPredicateRepository<Role,Long>  {
    Role findRoleByName(RoleName name);
}

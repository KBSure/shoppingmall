package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Member;

import java.util.List;

public interface MembersService {
    List<Member> getMembers();
    Member addMembers(Member member);
    Member getUserByEmail(String email);

}

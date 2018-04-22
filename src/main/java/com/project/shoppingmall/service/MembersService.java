package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Members;

import java.util.List;

public interface MembersService {
    List<Members> getMembers();
    Members addMembers(Members members);
    Members getUserByEmail(String email);

}

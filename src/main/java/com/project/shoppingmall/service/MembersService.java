package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Order;
import com.project.shoppingmall.dto.PasswordFormDTO;
import com.project.shoppingmall.dto.UpdateFormDTO;
import com.project.shoppingmall.dto.MemberFormDTO;

import java.util.List;

public interface MembersService {
    List<Member> getMembers();
    Member joinMembers(MemberFormDTO memberFormDTO);
    void updateMember(UpdateFormDTO updateFormDTO);
    void updateMemberPassword(PasswordFormDTO passwordFormDTO);
    Member getUserByEmail(String email);
    List<Order> getOrderList(Member member);

}

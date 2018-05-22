package com.project.shoppingmall.service;

import com.project.shoppingmall.domain.DeliveryState;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Order;
import com.project.shoppingmall.dto.PasswordFormDTO;
import com.project.shoppingmall.dto.UpdateFormDTO;
import com.project.shoppingmall.dto.MemberFormDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MembersService {
    List<Member> getMembers();
    Member joinMembers(MemberFormDTO memberFormDTO);
    void updateMember(UpdateFormDTO updateFormDTO);
    void updateMemberPassword(PasswordFormDTO passwordFormDTO);
    Member getUserByEmail(String email);
    Page<Order> getOrderList(Member member, int page, DeliveryState deliveryState);
    void dropout(Member member);
}

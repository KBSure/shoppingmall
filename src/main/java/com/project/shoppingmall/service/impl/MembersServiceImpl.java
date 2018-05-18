package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.*;
import com.project.shoppingmall.dto.PasswordFormDTO;
import com.project.shoppingmall.dto.UpdateFormDTO;
import com.project.shoppingmall.dto.MemberFormDTO;
import com.project.shoppingmall.repository.MemberStatusRepository;
import com.project.shoppingmall.repository.MembersRepository;
import com.project.shoppingmall.repository.RoleRepository;
import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
//진행중
@Service
public class MembersServiceImpl implements MembersService  {
    @Autowired
    MembersRepository membersRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    MemberStatusRepository memberStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMembers()
    {
        return membersRepository.findAll();
    }

    @Override
    @Transactional
    public Member joinMembers(MemberFormDTO memberFormDTO) {
        Member member = new Member();
        BeanUtils.copyProperties(memberFormDTO,member);
        member.setAddress(new Address(memberFormDTO.getPhone(), memberFormDTO.getZipcode(), memberFormDTO.getRePassword(), memberFormDTO.getDetail()));

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        Role role = roleRepository.findRoleByName(RoleName.USER); // USER, Role가져오기
        MemberStatus memberStatus = memberStatusRepository.findMemberStatusByStatus(MemberStat.NORMAL);
        member.addRole(role); // 권한 부여
        member.setMemberStatus(memberStatus); //상태부여
        member.setRegDate(LocalDateTime.now());

        return membersRepository.save(member);
    }

    @Override
    @Transactional
    public void updateMember(UpdateFormDTO updateFormDTO) {
        Member member = membersRepository.findMembersByEmail(updateFormDTO.getEmail());
        member.setAddress(new Address(updateFormDTO.getPhone(), updateFormDTO.getZipcode(), updateFormDTO.getLocation(), updateFormDTO.getDetail()));
    }

    @Override
    @Transactional
    public void updateMemberPassword(PasswordFormDTO passwordFormDTO) {
        Member member = membersRepository.findMembersByEmail(passwordFormDTO.getEmail());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        member.setPassword(passwordEncoder.encode(passwordFormDTO.getPassword()));

    }

    @Override
    @Transactional(readOnly = true)
    public Member getUserByEmail(String email) {
        return membersRepository.findMembersByEmail(email);
    }

}

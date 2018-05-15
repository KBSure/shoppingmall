package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Address;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Role;
import com.project.shoppingmall.dto.PasswordFormDTO;
import com.project.shoppingmall.dto.UpdateFormDTO;
import com.project.shoppingmall.dto.MemberFormDTO;
import com.project.shoppingmall.repository.MembersRepository;
import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MembersServiceImpl implements MembersService {
    @Autowired
    MembersRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Member> getMembers()
    {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Member joinMembers(MemberFormDTO memberFormDTO) {
        Member member = new Member();
        BeanUtils.copyProperties(memberFormDTO,member);
        member.setAddress(new Address(memberFormDTO.getPhone(), memberFormDTO.getZipcode(), memberFormDTO.getRePassword(), memberFormDTO.getDetail()));

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));

//        member.addRole(new Role().makeRole("USER"));

        return repository.save(member);
    }

    @Override
    @Transactional
    public void updateMember(UpdateFormDTO updateFormDTO) {
        Member member = repository.findMembersByEmail(updateFormDTO.getEmail());
        member.setAddress(new Address(updateFormDTO.getPhone(), updateFormDTO.getZipcode(), updateFormDTO.getLocation(), updateFormDTO.getDetail()));
    }

    @Override
    @Transactional
    public void updateMemberPassword(PasswordFormDTO passwordFormDTO) {
        Member member = repository.findMembersByEmail(passwordFormDTO.getEmail());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        member.setPassword(passwordEncoder.encode(passwordFormDTO.getPassword()));

    }

    @Override
    @Transactional(readOnly = true)
    public Member getUserByEmail(String email) {
        return repository.findMembersByEmail(email);
    }

}

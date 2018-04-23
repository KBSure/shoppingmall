package com.project.shoppingmall.service.impl;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.repository.MembersRepository;
import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Member addMembers(Member member) {
        return repository.save(member);
    }

    @Override
    public Member getUserByEmail(String email) {
        return repository.findMembersByEmail(email);
    }
}

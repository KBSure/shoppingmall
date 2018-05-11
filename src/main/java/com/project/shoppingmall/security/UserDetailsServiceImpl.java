package com.project.shoppingmall.security;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Role;
import com.project.shoppingmall.service.LoginMember;
import com.project.shoppingmall.service.MembersService;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Log4j2
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    MembersService service;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Member member = service.getUserByEmail(name);

        List<GrantedAuthority> list = new ArrayList<>();
       // member.getRoles().forEach(role -> list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
        for(Role role : member.getRoles()) {
            GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
            list.add(simpleGrantedAuthority);
        }
        LoginMember loginMember = new LoginMember(member.getEmail(), member.getPasswd(),list);
        loginMember.setId(member.getId());
        loginMember.setName(member.getName());
        return loginMember;
    }
}
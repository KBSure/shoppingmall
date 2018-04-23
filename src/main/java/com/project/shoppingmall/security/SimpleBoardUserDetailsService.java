package com.project.shoppingmall.security;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Role;
import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleBoardUserDetailsService implements UserDetailsService {
    @Autowired
    MembersService service;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Member member = service.getUserByEmail(name);

        List<GrantedAuthority> list = new ArrayList<>();
       // member.getRoles().forEach(role -> list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
        for(Role role : member.getRoles()) {
            GrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
            list.add(simpleGrantedAuthority);
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(member.getEmail(), member.getPasswd(), list);
        return userDetails;
    }
}
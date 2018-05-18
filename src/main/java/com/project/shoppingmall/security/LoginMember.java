package com.project.shoppingmall.security;


import com.project.shoppingmall.domain.MemberStat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
//진행중
public class LoginMember extends User  {
    private final Long id;
    private final String name;
    private final MemberStat memberStat;

    public LoginMember(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id, String name, MemberStat memberStat) {
        super(username, password, authorities);
        this.id = id;
        this.name = name;
        this.memberStat = memberStat;
    }

    public LoginMember(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Long id, String name, MemberStat memberStat) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.name = name;
        this.memberStat = memberStat;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public MemberStat getMemberStat() {
        return memberStat;
    }
}

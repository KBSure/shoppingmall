package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
//@Entity
//@Table(name = "members_roles")
public class MemberRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "members_id")
    private Member member;
    @JoinColumn(name = "roles_id")
    private Role role;
    
//    public void setMember(Member member) {
//        this.member = member;
//        member.getMemberRoles().add(this);
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//        role.getMemberRoles().add(this);
//    }
}

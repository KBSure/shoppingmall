package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "member_status")
public class MemberStatus implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    private MemberStat status;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberStatus")
    private List<Member> members = new ArrayList<>();
    
    public void addMember(Member member) {
        if(!this.members.contains(member)) {
            this.members.add(member);
        }
        member.setMemberStatus(this);
    }
}

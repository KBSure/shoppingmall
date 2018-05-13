package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="roles")
@Getter
@Setter
@EqualsAndHashCode
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<Member> members = new ArrayList<>();
    
    public void addMember(Member member) {
        members.add(member);
        if(!member.getRoles().contains(this)) {
            member.getRoles().add(this);
        }
    }

    public Role makeRole(String name){
        this.name = name;
        return this;
    }
    
}

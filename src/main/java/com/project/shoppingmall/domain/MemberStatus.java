package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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
    private MemberStat state;
    @OneToMany(mappedBy = "memberStatus", cascade = CascadeType.ALL)
    private List<CurrentMemberStatus> currentMemberStatus = new ArrayList<>();
    
    public void addCurrentMemberStatus(CurrentMemberStatus currentMemberStatus) {
        if(!this.currentMemberStatus.contains(currentMemberStatus)) {
            this.currentMemberStatus.add(currentMemberStatus);
        }
        currentMemberStatus.setMemberStatus(this);
    }
    
}

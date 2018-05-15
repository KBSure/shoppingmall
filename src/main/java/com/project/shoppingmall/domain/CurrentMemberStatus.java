package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "current_member_status")
public class CurrentMemberStatus {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_status_id")
    private MemberStatus memberStatus;
    
    public void setMemberStatus(MemberStatus memberStatus) {
        if(this.memberStatus != null) {
            this.memberStatus.getCurrentMemberStatus().remove(this);
        }
        this.memberStatus = memberStatus;
        memberStatus.getCurrentMemberStatus().add(this);
    }
    
    @OneToOne(mappedBy = "currentMemberStatus")
    private Member member;
    
    public void setMember(Member member) {
        this.member = member;
        member.setCurrentMemberStatus(this);
    }
}

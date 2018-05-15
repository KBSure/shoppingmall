package com.project.shoppingmall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="members")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    
    @JsonIgnore
    @Column(name = "password")
    private String password;

    private String name;
    
    @Embedded
    private Address address;
    
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "members_roles"
            , joinColumns = @JoinColumn(name = "members_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles = new ArrayList<>();
    
    public void addRole(Role role) {
        roles.add(role);
        if(!role.getMembers().contains(this)) {
            role.getMembers().add(this);
        }
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
    
    public void addOrder(Order order) {
        if(!this.orders.contains(this)) {
            this.orders.add(order);
        }
        order.setMember(this);
    }
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "member")
    private Cart cart;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "member")
    private Wishlist wishlist;
    
    @OneToOne
    @JoinColumn(name = "current_member_status_id")
    private CurrentMemberStatus currentMemberStatus;
    
    public void setCurrentMemberStatus(CurrentMemberStatus currentMemberStatus) {
        this.currentMemberStatus = currentMemberStatus;
        currentMemberStatus.setMember(this);
    }
    
}

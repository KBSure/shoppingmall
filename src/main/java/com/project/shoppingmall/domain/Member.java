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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_status_id")
    private MemberStatus memberStatus;
    private LocalDateTime status_start_date;
    private LocalDateTime status_end_date;
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<WishItem> wishItems = new ArrayList<>();

    public void addRole(Role role) {
        if(!this.roles.contains(role)) {
            this.roles.add(role);
        }
        
        if(!role.getMembers().contains(this)) {
            role.getMembers().add(this);
        }
        
    }

    public void addOrder(Order order) {
        if(!this.orders.contains(order)) {
            this.orders.add(order);
        }
        order.setMember(this);
    }
    
    public void addCartItem(CartItem cartItem) {
        if(!this.cartItems.contains(cartItem)) {
            this.cartItems.add(cartItem);
        }
        cartItem.setMember(this);
    }
    
    public void addWishItem(WishItem wishItem) {
        if(!this.wishItems.contains(wishItem)) {
            this.wishItems.add(wishItem);
        }
        wishItem.setMember(this);
    }
    
    public void setMemberStatus(MemberStatus memberStatus) {
        if(this.memberStatus != null) {
            this.memberStatus.getMembers().remove(this);
        }
        this.memberStatus = memberStatus;
        memberStatus.getMembers().add(this);
    }
}

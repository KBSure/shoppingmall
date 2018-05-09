package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    
    @Embedded
    private Address address;
    
    @Embedded
    private BankAccount bankAccount;
    
    @Column(name = "shipping_charge")
    private int shippingCharge;
    private String receiver;
    private String message;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "members_id")
    private Member member;
    
    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public void addOrderItem(OrderItem orderItem) {
        if(!this.orderItems.contains(orderItem)) {
            this.orderItems.add(orderItem);
        }
        orderItem.setOrder(this);
    }
}

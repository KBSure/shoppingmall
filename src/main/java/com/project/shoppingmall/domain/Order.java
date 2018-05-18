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
    
    private String receiver;
    private String message;
    private String invoice;
    
    @Enumerated(value = EnumType.STRING)
    @Column(name = "state")
    private DeliveryState deliveryState;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "members_id")
    private Member member;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();


    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        if(!this.orderItems.contains(orderItem)) {
            this.orderItems.add(orderItem);
        }
        orderItem.setOrder(this);
    }
}

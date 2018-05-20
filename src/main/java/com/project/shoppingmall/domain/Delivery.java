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
@Table(name = "delivery")
public class Delivery implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "shipping_charge")
    private int shippingCharge;
    
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
    
    public void addOrder(Order order) {
        if(!this.orders.contains(order)) {
            this.orders.add(order);
        }
        order.setDelivery(this);
    }
    
}

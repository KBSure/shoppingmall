package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String invoice;
    @Enumerated(EnumType.STRING)
    private DeliveryState state;
    private String company;
    private int price;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int hit;
    @JoinColumn(name = "order_id")
    private Order order;
    @JoinColumn(name = "product_id")
    private Product product;
    
    public void setOrder(Order order) {
        this.order = order;
        if(!order.getOrderItems().contains(this)) {
            order.getOrderItems().add(this);
        }
    }
    
    public void setProduct(Product product) {
        this.product = product;
        if(!product.getOrderItems().contains(this)) {
            product.getOrderItems().add(this);
        }
    }

}

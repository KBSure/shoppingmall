package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
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
        if(this.order != null) {
            this.order.getOrderItems().remove(this);
        }
        this.order = order;
        order.getOrderItems().add(this);
    }
    
    public void setProduct(Product product) {
        if(this.product != null) {
            this.product.getOrderItems().remove(this);
        }
        this.product = product;
        product.getOrderItems().add(this);
    }

}

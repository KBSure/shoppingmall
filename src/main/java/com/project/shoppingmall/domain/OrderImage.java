package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "order_image")
public class OrderImage implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Column(name = "mime_type")
    private String mimeType;
    
    @Enumerated(EnumType.STRING)
    private ImageType type;
    private int size;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id")
    private OrderItem orderItem;
    
    public void setOrderItem(OrderItem orderItem) {
        if(this.orderItem != null) {
            this.orderItem.getOrderImages().remove(orderItem);
        }
        this.orderItem = orderItem;
        orderItem.getOrderImages().add(this);
    }
    
}

package com.project.shoppingmall.domain;

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
@Table(name = "order_item")
public class OrderItem implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private int quantity;
    @Column(name = "product_price")
    private int productPrice;
    @Column(name = "product_name")
    private String productName;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private Order order;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "orderItem")
    private List<OrderImage> orderImages = new ArrayList<>();


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

    public void addOrderImage(OrderImage orderImage) {
        if(!this.orderImages.contains(orderImage)) {
            this.orderImages.add(orderImage);
        }
        orderImage.setOrderItem(this);
    }
    
}

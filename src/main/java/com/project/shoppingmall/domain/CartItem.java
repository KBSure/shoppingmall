package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "cart_item")
public class CartItem implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getCartItems().remove(this);
        }
        this.member = member;
        member.getCartItems().add(this);
    }

    public void setProduct(Product product) {
        if(this.product != null) {
            this.product.getCartItems().remove(this);
        }
        this.product = product;
        product.getCartItems().add(this);
    }
    
    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }
    
}

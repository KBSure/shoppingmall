package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private int quantity;
    @JoinColumn(name="members_id")
    private Member member;
    @JoinColumn(name = "product_id")
    private Product product;
    
    public void setMember(Member member) {
        this.member = member;
        if(!member.getCartList().contains(this)) {
            member.getCartList().add(this);
        }
    }
    
    public void setProduct(Product product) {
        this.product = product;
        if(!product.getCartList().contains(this)) {
            product.getCartList().add(this);
        }
    }
    
}

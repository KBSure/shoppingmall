package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
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
        if(this.member != null) {
            this.member.getCartList().remove(this);
        }
        this.member = member;
        member.getCartList().add(this);
    }
    
    public void setProduct(Product product) {
        if(this.product != null) {
            this.product.getCartList().remove(this);
        }
        this.product = product;
        product.getCartList().add(this);
    }
    
}

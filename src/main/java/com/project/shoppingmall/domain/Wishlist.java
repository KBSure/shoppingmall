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
@Table(name = "wish_list")
public class Wishlist implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private int quantity;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="members_id")
    private Member member;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    
    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getWishlists().remove(this);
        }
        this.member = member;
        member.getWishlists().add(this);
    }
    
    public void setProduct(Product product) {
        if(this.product != null) {
            this.product.getWishlists().remove(this);
        }
        this.product = product;
        member.getWishlists().add(this);
    }
    
    
}

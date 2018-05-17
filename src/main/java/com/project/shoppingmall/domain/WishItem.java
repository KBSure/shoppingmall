package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "wish_item")
public class WishItem implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    
    public void setMember(Member member) {
        if(this.member != null) {
            this.member.getWishItems().remove(this);
        }
        this.member = member;
        member.getWishItems().add(this);
    }

    public void setProduct(Product product) {
        if(this.product != null) {
            this.product.getWishItems().remove(this);
        }
        this.product = product;
        product.getWishItems().add(this);
    }
    
}

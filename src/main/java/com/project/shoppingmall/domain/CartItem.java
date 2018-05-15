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
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cart_id")
    private Cart cart;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;


    public void setCart(Cart cart) {
        if(this.cart != null) {
            this.cart.getCartItems().remove(this);
        }
        this.cart = cart;
        cart.addCartItem(this);
    }

    public void setProduct(Product product) {
        if(this.product != null) {
            this.product.getCartItems().remove(this);
        }
        this.product = product;
        product.getCartItems().add(this);
    }
    
}

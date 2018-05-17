package com.project.shoppingmall.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode
@ToString
public class CartInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long prdId;
    private int quantity;
    private String productName;
    private int price;
    private Long imageId;
    
    public void updateQuantity(int quantity) {
        this.quantity += quantity;
    }
    
}

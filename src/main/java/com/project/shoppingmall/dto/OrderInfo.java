package com.project.shoppingmall.dto;

import com.project.shoppingmall.domain.DeliveryState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode
@ToString
public class OrderInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long prdId;
    private int quantity;
    private String productName;
    private int price;
    private Long imageId;
    private DeliveryState deliveryState;
    private LocalDateTime regDate;
    
}

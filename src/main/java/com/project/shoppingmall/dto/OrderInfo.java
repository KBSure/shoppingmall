package com.project.shoppingmall.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long memberId;
    private Long prdId;
    private int quantity;
    private String productName;
    private int price;
    private Long imageId;
    private String receiver;
    private String adrZipcode;
    private String adrLocation;
    private String adrDetail;
    private String phone;
    private String message;
    private String bank;
    private String account;
    private String depositor;
    
}

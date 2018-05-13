package com.project.shoppingmall.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@EqualsAndHashCode
public class CartInfo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @NotNull
    @Min(1)
    private Long prdId;
    @Min(1)
    @Max(20)
    private int quantity;
    
}

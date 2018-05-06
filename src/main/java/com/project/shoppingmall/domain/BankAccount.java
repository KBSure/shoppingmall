package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class BankAccount implements Serializable {

    private String depositor;
    private String bank;
    private String account;
    
}

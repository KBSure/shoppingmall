package com.project.shoppingmall.domain;

import javax.persistence.Embeddable;

@Embeddable
public class BankAccount {

    private String depositor;
    private String bank;
    private String account;
    
}

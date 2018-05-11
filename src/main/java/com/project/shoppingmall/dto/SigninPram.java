package com.project.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SigninPram {
    long id;
    String name;

    public SigninPram(long id, String name) {
        this.id = id;
        this.name = name;
    }
}

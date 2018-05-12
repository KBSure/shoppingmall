package com.project.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SigninParam {
    String id;
    String name;

    public SigninParam(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

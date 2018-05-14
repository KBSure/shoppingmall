package com.project.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class PasswordFormDTO {

    String email;

    @NotBlank
    @Pattern(regexp="([a-zA-Z0-9].*){8,20}"
            ,message="숫자 영문자를 포함한 8 ~ 20자를 입력하세요. ")
    String password;

    @NotBlank
    @Pattern(regexp="([a-zA-Z0-9].*){8,20}"
            ,message="숫자 영문자를 포함한 8 ~ 20자를 입력하세요. ")
    String rePassword;
}

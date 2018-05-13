package com.project.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.*;

@Getter
@Setter

public class JoinFormDTO {
    @NotBlank
    @Size(min= 2, max = 10, message = "2자에서 10자 사이의 값만 가능합니다.")
    String name;
    @NotBlank
    String email;
    @NotBlank
    @Pattern(regexp="([a-zA-Z0-9].*){8,20}"
            ,message="숫자 영문자를 포함한 8 ~ 20자를 입력하세요. ")
    String password;
    @NotBlank
    @Pattern(regexp="([a-zA-Z0-9].*){8,20}"
            ,message="숫자 영문자를 포함한 8 ~ 20자를 입력하세요. ")
    String rePassword;
    @NotBlank
    @Pattern(regexp="(^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$)", message = "010-0000-0000형식으로 입력해 주세요.")
    String phone;
    @NotBlank
    @Pattern(regexp="(^[0-9]{5}$)", message = "5자리의 숫자만 가능합니다.")
    String zipcode;
    @NotBlank
    String location;
    @NotBlank
    String detail;
}

package com.project.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UpdateFormDTO {

    String name;
    String email;
    @NotBlank
    @Pattern(regexp="(^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$)", message = "010-0000-0000형식으로 입력해 주세요.")
    String phone;
    @NotBlank
    String zipcode;
    @NotBlank
    String location;
    @NotBlank
    String detail;

    public void setAdderss(String phone,String zipcode, String location, String detail){
        this.phone = phone;
        this.zipcode = zipcode;
        this.location = location;
        this.detail = detail;

    }
}

package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class Address implements Serializable {

    public Address(String phone, String zipcode, String location, String detail) {
        this.phone = phone;
        this.zipcode = zipcode;
        this.location = location;
        this.detail = detail;
    }

    private String phone;
    @Column(name = "adr_zipcode")
    private String zipcode;
    @Column(name = "adr_location")
    private String location;
    @Column(name = "adr_detail")
    private String detail;
    
}

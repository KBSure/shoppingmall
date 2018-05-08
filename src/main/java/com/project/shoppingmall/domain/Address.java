package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class Address implements Serializable {
    
    private String phone;
    @Column(name = "adr_zipcode")
    private String zipcode;
    @Column(name = "adr_location")
    private String location;
    @Column(name = "adr_detail")
    private String detail;
    
}

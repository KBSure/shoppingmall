package com.project.shoppingmall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="members")
@Getter
@Setter
public class Members implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    @JsonIgnore
    private String passwd;
    private String name;
    private String phone;
    private String address;

}

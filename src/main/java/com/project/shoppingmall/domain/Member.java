package com.project.shoppingmall.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="members")
@Getter
@Setter
public class Member implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    @JsonIgnore
    private String passwd;
    private String name;
    private String phone;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //lazy
    @JoinTable(name="members_roles",joinColumns = @JoinColumn(name="members_id"),inverseJoinColumns = @JoinColumn(name="roles_id"))
    private List<Role> roles;



}

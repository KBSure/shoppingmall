package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "persistence_login")
public class PersistenceLogin implements Serializable {
    @Id
    private String series;
    private String username;
    private String token;
    @Column(name = "last_used")
    private Date lastUsed;
}
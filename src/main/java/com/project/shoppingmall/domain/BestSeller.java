package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "best_seller")
public class BestSeller implements Serializable {
    
    @Id
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @MapsId
    private Product product;
    
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    
    
    public void setProduct(Product product) {
        this.product = product;
        product.setBestSeller(this);
    }
}

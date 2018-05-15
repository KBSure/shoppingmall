package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "best_seller")
public class BestSeller {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    
    public void setProduct(Product product) {
        this.product = product;
        product.setBestSeller(this);
    }
}

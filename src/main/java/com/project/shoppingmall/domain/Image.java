package com.project.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "iamge")
public class Image implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "mime_type")
    private String mimeType;
    @Enumerated(EnumType.STRING)
    private ImageType type;
    private int size;
    @JoinColumn(name = "product_id")
    private Product product;
    
    public void setProduct(Product product) {
        this.product = product;
        if(!product.getImages().contains(this)) {
            product.getImages().add(this);
        }
    }
}

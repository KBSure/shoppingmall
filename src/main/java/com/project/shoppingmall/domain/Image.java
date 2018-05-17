package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "image")
public class Image implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Column(name = "mime_type")
    private String mimeType;
    
    @Enumerated(EnumType.STRING)
    private ImageType type;
    private int size;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    private Product detailProduct;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_thumb_id")
    private Product thumbProduct;
    
    public void setDetailProduct(Product detailProduct) {
        if(this.detailProduct != null) {
            this.detailProduct.getDetailImages().remove(this);
        }
        detailProduct.getDetailImages().add(this);
        this.detailProduct = detailProduct;
    }
    
    public void setThumbProduct(Product thumbProduct) {
        if(this.thumbProduct != null) {
            this.thumbProduct.getThumbImages().remove(this);
        }
        this.thumbProduct = thumbProduct;
        thumbProduct.getThumbImages().add(this);
    }
    
}

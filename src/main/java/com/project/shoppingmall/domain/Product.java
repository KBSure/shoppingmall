package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "product")
public class Product implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int width;
    private int height;
    private int quantity;
    private int price;
    
    @Lob
    private String content;
    
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    @Enumerated(EnumType.STRING)
    private ProductState state;
    
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BestSeller bestSeller;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<CartItem> cartItems = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<WishItem> wishItems = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailProduct")
    private List<Image> detailImages = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "thumbProduct")
    private List<Image> thumbImages = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();
    
    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Category category;

    public void addCartItem(CartItem cartItem) {
        if(!this.cartItems.contains(cartItem)) {
            this.cartItems.add(cartItem);
        }
        cartItem.setProduct(this);
    }

    public void addWishItem(WishItem wishItem) {
        if(!this.wishItems.contains(wishItem)) {
            this.wishItems.add(wishItem);
        }
        wishItem.setProduct(this);
    }

    public void addDetailImage(Image detailImage) {
        if(!this.detailImages.contains(detailImage)) {
            this.detailImages.add(detailImage);
        }
        detailImage.setDetailProduct(this);
    }
    
    public void addThumbImage(Image thumbImage) {
        if(!this.thumbImages.contains(thumbImage)) {
            this.thumbImages.add(thumbImage);
        }
        thumbImage.setThumbProduct(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        if(!this.orderItems.contains(orderItem)) {
            this.orderItems.add(orderItem);
        }
        orderItem.setProduct(this);
    }

    public void setCategory(Category category) {
        if(this.category != null) {
            this.category.getProducts().remove(this);
        }
        this.category = category;
        category.getProducts().add(this);
    }
    
}

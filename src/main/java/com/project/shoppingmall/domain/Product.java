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
    
    @Column(name = "shipping_charge")
    private int shippingCharge;
    private int width;
    private int height;
    private int quantity;
    private int price;
    
    @Lob
    private String content;
    private boolean bestSeller;
    
    @Column(name = "reg_date")
    private LocalDateTime regDate;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Cart> cartList = new ArrayList<>();
    
    public void addCart(Cart cart) {
        if(!this.cartList.contains(cart)) {
            this.cartList.add(cart);
        }
        cart.setProduct(this);
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Wishlist> wishlists = new ArrayList<>();
    
    public void addWishlist(Wishlist wishlist) {
        if(!this.wishlists.contains(wishlist)) {
            this.wishlists.add(wishlist);
        }
        wishlist.setProduct(this);
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Image> images = new ArrayList<>();
    
    public void addImage(Image image) {
        if(!this.images.contains(image)) {
            this.images.add(image);
        }
        image.setProduct(this);
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();
    
    public void addOrderItem(OrderItem orderItem) {
        if(!this.orderItems.contains(orderItem)) {
            this.orderItems.add(orderItem);
        }
        orderItem.setProduct(this);
    }
    
    @JoinColumn(name = "category_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    
    public void setCategory(Category category) {
        if(this.category != null) {
            this.category.getProducts().remove(this);
        }
        this.category = category;
        category.getProducts().add(this);
    }
    
}

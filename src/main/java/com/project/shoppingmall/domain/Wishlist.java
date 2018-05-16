package com.project.shoppingmall.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@EqualsAndHashCode
@Entity
@Table(name = "wish_list")
public class Wishlist implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="members_id")
    private Member member;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wishlist")
    private List<WishItem> wishItems = new ArrayList<>();
    
    public void addWishItem(WishItem wishItem) {
        if(!this.wishItems.contains(wishItem)) {
            this.wishItems.add(wishItem);
        }
        wishItem.setWishlist(this);
    }
    
}

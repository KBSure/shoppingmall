package com.project.shoppingmall.controller.api;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.WishItem;
import com.project.shoppingmall.service.MembersService;
import com.project.shoppingmall.service.ProductService;
import com.project.shoppingmall.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListApiController {
    @Autowired
    WishListService wishListService;

    @Autowired
    MembersService membersService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<String> postWishList(@RequestParam(name = "prdId", required = true)List<Long> prdIdList, Principal principal){
        String wishListUrl = "/wishlist";
    
        if(principal == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
//        이미 wishlist에 있을 경우, 추가하지 않고, alert() 한다.
        List<Product> productList = productService.getProducts(prdIdList);
        Member member = membersService.getUserByEmail(principal.getName());
    
        WishItem wishItem = new WishItem();
        wishItem.setMember(member);
        wishListService.registWishlist(member, productList);
        return new ResponseEntity<>(wishListUrl, HttpStatus.OK);
    }
}

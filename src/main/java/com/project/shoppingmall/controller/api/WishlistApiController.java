package com.project.shoppingmall.controller.api;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.service.MembersService;
import com.project.shoppingmall.service.ProductService;
import com.project.shoppingmall.service.WishlistService;
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
public class WishlistApiController {
    @Autowired
    WishlistService wishlistService;

    @Autowired
    MembersService membersService;

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<String> postWishList(@RequestParam(name = "prdId", required = true)List<Long> prdIdList, Principal principal){
        String wishListUrl = "/wishlist";
//        이미 wishlist에 있을 경우, 추가하지 않고, alert() 한다.
        List<Product> productList = productService.getProducts(prdIdList);
        Member member = membersService.getUserByEmail(principal.getName());

//        wishlistService.addWishlist(productList, member);
        return new ResponseEntity<>(wishListUrl, HttpStatus.OK);
    }
}

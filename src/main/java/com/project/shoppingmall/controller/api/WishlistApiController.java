package com.project.shoppingmall.controller.api;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.domain.Wishlist;
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
    MembersService membersService;
    ProductService productService;

    @PostMapping
    public ResponseEntity<String> postWishList(@RequestParam(name = "prdId", required = true)List<Long> prdIdList, Principal principal){
        String wishListUrl = "/wishlist";

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(principal.getName());

//        ProductID 받는다.
//        pricipal로 memberId 가져온다.
//        product 객체들 가져온다.
//        member 가져온다.
         // 이하 wishlist service에서
        //wishlist 객체 넣는다.
        //wishlist를 add한다.

        List<Product> productList = productService.getProducts(prdIdList);
        Member member = membersService.getUserByEmail(principal.getName());

        wishlistService.addWIshlist(productList, member);



//        Member member = membersService.getUserByEmail(principal.getName());
//
//        List<Product> list = productService.getProducts(prdIdList);
//
//        for(Product p : list){
//            Wishlist wishlist = new Wishlist();
//            wishlist.setMember(member);
//            wishlist.setProduct(p);
//            Wishlist wishlist1 = wishlistService.addWishlist(wishlist);
//            System.out.println(wishlist1);
//        }

//        wishlist.getProduct(prdIdList.get(1));



        return new ResponseEntity<String>(wishListUrl, HttpStatus.OK);
    }
}

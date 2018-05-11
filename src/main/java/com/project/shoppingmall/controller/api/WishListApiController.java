package com.project.shoppingmall.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wishlist")
public class WishListApiController {
    @PostMapping
    public ResponseEntity<String> postWishList(){
        String wishListUrl = "/wishlist";
        return new ResponseEntity<String>(wishListUrl, HttpStatus.OK);
    }
}

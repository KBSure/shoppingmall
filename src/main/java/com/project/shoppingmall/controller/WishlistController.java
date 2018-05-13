package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Wishlist;
import com.project.shoppingmall.service.ProductService;
import com.project.shoppingmall.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    WishlistService wishlistService;

    @Autowired
    ProductService productService;

    @GetMapping
    public String getWishList(@RequestParam(name = "prd_cate", required = false)String prdCate, @RequestParam(name = "page", defaultValue = "1")int page,
                              @RequestParam(name = "prd_id", required = false)Long prdId, @RequestParam(name = "prd_cnt", defaultValue = "0")int prdCnt, ModelMap modelmap, Principal principal){

        List<Wishlist> wishlists = wishlistService.getWishlists(principal.getName());
        modelmap.addAttribute("wishlists", wishlists);

        return "/wishlist/wishlist";
    }


    @DeleteMapping
    public String deleteWishList(){

        return "redirect:/wishlist";
    }
}

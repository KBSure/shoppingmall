package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishListController {

    @GetMapping
    public String getWishList(@RequestParam(name = "prd_cate", required = false)String prdCate, @RequestParam(name = "page", defaultValue = "1")int page,
                              @RequestParam(name = "prd_id", required = false)Long prdId, @RequestParam(name = "prd_cnt", defaultValue = "0")int prdCnt, ModelMap modelmap){

        return "/wishlist/wishlist";
    }


    @DeleteMapping
    public String deleteWishList(){

        return "redirect:/wishlist";
    }
}

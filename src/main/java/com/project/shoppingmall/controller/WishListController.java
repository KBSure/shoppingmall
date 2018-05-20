package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.WishItem;
import com.project.shoppingmall.service.ProductService;
import com.project.shoppingmall.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    ProductService productService;

    @GetMapping
    public String getWishList(@RequestParam(name = "prd_cate", required = false) String prdCate
            , @RequestParam(name="sort_type", required = false) String sortType, @RequestParam(defaultValue = "1") int page
            , @RequestParam(name="search_str", required = false) String searchStr, ModelMap modelmap, Principal principal){

        List<WishItem> wishList = wishListService.getWishList(principal.getName());

        modelmap.addAttribute("wishList", wishList);
        modelmap.addAttribute("page", page);
        modelmap.addAttribute("searchStr", searchStr);
        modelmap.addAttribute("prdCate", prdCate);
        modelmap.addAttribute("sortType", sortType);

        return "/wishlist/wishlist";
    }


    @DeleteMapping
    public String deleteWishList(@RequestParam(name = "wish_item_id", required = false)List<Long> wishItemIdList, ModelMap modelMap){
        //wishItemIdList를 이용해서 wishItem들을 삭제할 것입니다.
        if(wishItemIdList != null)
            wishListService.deleteWishList(wishItemIdList);
        return "redirect:/wishlist";
    }
}

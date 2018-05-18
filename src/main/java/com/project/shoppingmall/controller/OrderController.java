package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.CartItem;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Order;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.dto.OrderInfo;
import com.project.shoppingmall.security.LoginMember;
import com.project.shoppingmall.service.CartService;
import com.project.shoppingmall.service.MembersService;
import com.project.shoppingmall.service.OrderService;
import com.project.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private MembersService membersService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private CartService cartService;
    
    //장바구니
    @GetMapping("/cart")
    public String getCart(HttpSession session, Authentication authentication, Model model){
        // 세션에서 카트 내역 조회
        @SuppressWarnings("unchecked")
        Map<Long, CartInfo> cartInfoMap = (Map<Long, CartInfo>) session.getAttribute("cartInfoMap");
        
        // 세션에 담긴 카트정보가 없고, 로그인 사용자이면, DB에서 사용자 카트 조회하여 카트정보에 담기.
        if(authentication != null) {
            LoginMember loginMember = (LoginMember) authentication.getPrincipal();
            if(cartInfoMap != null && !cartInfoMap.isEmpty()) {
                // 로그인 사용자 이면서, 세션에 담긴 저장하지 않은 카트정보가 있으면,
                if(session.getAttribute("cartRegist") == null) {
                    // 세션에 담긴정보 디비에 저장
                    cartService.registCart(loginMember.getId(), cartInfoMap);
                }

                // 세션에서 카트정보 삭제.
                session.removeAttribute("cartInfoMap");
            }
            List<CartItem> memberCart = cartService.getMemberCart(loginMember.getId());
    
            List<CartInfo> cartInfoList = memberCart.stream().map(item -> makeCartInfo(item)).collect(toList());
            model.addAttribute("cart", cartInfoList);
            return "order/cart";
        }
        
        List<CartInfo> cartInfoList = new ArrayList<>();
        if(cartInfoMap != null) {
            cartInfoList.addAll(cartInfoMap.values());
        }
        model.addAttribute("cart", cartInfoList);
        return "order/cart";
    }
    
    private CartInfo makeCartInfo(CartItem item) {
        CartInfo cartInfo = new CartInfo();
        
        Product product = item.getProduct();
        cartInfo.setPrdId(product.getId());
        cartInfo.setProductName(product.getName());
        cartInfo.setPrice(product.getPrice());
        cartInfo.setImageId(product.getThumbImages().get(0).getId());
        cartInfo.setQuantity(item.getQuantity());
        return cartInfo;
    }
    
    @PostMapping("/wishlist")
    @ResponseBody
    public ResponseEntity<String> registWishlist(HttpServletResponse response) {
    
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //주문페이지
    @PostMapping("/orderform")
    public String orderForm(@RequestParam(name = "prdId") List<Long> productIds, Authentication authentication
            , @RequestParam(name = "quantity") List<Integer> quantities, Model model){
        
        if(authentication == null) {
            throw new IllegalArgumentException("로그인 하지 않은 사용자 입니다.");
        }
        // 상품정보 조회
        List<Product> products = productService.getProducts(productIds);
    
        List<OrderInfo> orderInfos = makeOrderInfos(products, quantities);
        int totalPrice = orderInfos.stream().mapToInt(i -> i.getPrice() * i.getQuantity()).reduce(0, (i1, i2) -> i1 + i2);
        
        // 사용자 정보 조회후 페이지에 전달
        LoginMember loginMember = (LoginMember) authentication.getPrincipal();
        Member member = membersService.getUserByEmail(loginMember.getUsername());
    
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("orderInfos", orderInfos);
        model.addAttribute("member", member);
        
        return "order/order";
    }
    
    private List<OrderInfo> makeOrderInfos(List<Product> products, List<Integer> quantities) {
        List<OrderInfo> orderInfos = new ArrayList<>();
        int index = 0;
        for (Product product : products) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setPrdId(product.getId());
            orderInfo.setPrice(product.getPrice());
            orderInfo.setQuantity(quantities.get(index++));
            orderInfo.setImageId(product.getThumbImages().get(0).getId());
            orderInfo.setProductName(product.getName());
            orderInfos.add(orderInfo);
        }
        return orderInfos;
    }
    
    //주문
    @PostMapping("/order")
    public String order(@RequestParam(name = "prdId") List<Long> productIds, @RequestParam(name = "quantity") List<Integer> quantities
                        , Authentication authentication, Model model){
    
        // 상품 번호로 재고수량이 0이하인거 조회
        List<Product> soldOutProducts = productService.getSoldOutProducts(productIds);
        
        // 재고수량이 0 이하이면 오류.
        // TODO 오류 처리 다시 해야댐.
        if(!soldOutProducts.isEmpty()) {
            model.addAttribute("soldOut", true);
            
            String soldOutNames = soldOutProducts.stream().map(Product::getName).collect(Collectors.joining(", "));
            model.addAttribute("soldOutNames", soldOutNames);
            
            return "order/order";
        }
        
        // 해당 상품 재고수량 감소.
        
        // 감소 후 재고수량이 0 이하이면 오류.
        
        // 주문테이블 및 주문 아이템 테이블에 주문정보 삽입
        
        // 주문 완료 페이지로 이동.
        
        return null;
    }
    
    //주문완료 페이지
    @GetMapping("/{id}/success")
    public String success(@PathVariable(name = "id", required = false) Long id){
        //주문 완료된 상품 정보와 배송 정보들을 가져온다.
        return "order/success";
    }
}

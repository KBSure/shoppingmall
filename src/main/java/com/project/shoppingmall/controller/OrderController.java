package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.CartItem;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.security.LoginMember;
import com.project.shoppingmall.service.CartService;
import com.project.shoppingmall.service.OrderService;
import com.project.shoppingmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Controller
@RequestMapping("/order")
public class OrderController {
    
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
        
        //TODO 새로고침시 중복으로 저장 방지 필요.
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
    
    private void addCartItemsToCartInfoMap(Map<Long, CartInfo> cartInfoMap, List<CartItem> cartItems) {
        for (CartItem registItem : cartItems) {
            cartInfoMap.put(registItem.getId(), makeCartInfo(registItem));
        }
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
    
    private void addProductInfo(List<CartInfo> cartList, Map<Long, Product> productMap) {
        cartList.forEach(c -> {
            Product product = productMap.get(c.getPrdId());
            c.setPrice(product.getPrice());
            c.setImageId(product.getDetailImages().get(0).getId());
            c.setProductName(product.getName());
        });
    }
    
//    private List<CartInfo> makeCartInfoList(List<Cart> memberCarts, Map<Long, Product> productMap) {
//        List<CartInfo> cartList = new ArrayList<>();
//        memberCarts.forEach(cart -> {
//            Product product = productMap.get(cart.getDetailProduct().getId());
//            if(product == null) throw new IllegalArgumentException("존재하지 않는 상품입니다.");
//            CartInfo cartInfo = makeCartInfo(cart, product);
//            cartList.add(cartInfo);
//        });
//        return cartList;
//    }

    
    @PostMapping("/wishlist")
    @ResponseBody
    public ResponseEntity<String> registWishlist(HttpServletResponse response) {
    
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public String deleteCart(@RequestParam(name = "prd_cate", required = false)String prdCate, @RequestParam(name = "page", defaultValue = "1")int page,
                       @RequestParam(name = "prd_id", required = false)Long prdId, @RequestParam(name = "prd_cnt", defaultValue = "0")int prdCnt){
        // DB에서 해당 데이터 삭제
        return "redirect:/order/cart";
    }

    //주문페이지
    @GetMapping("/order")
    public String orderForm(@RequestParam(name = "prd_id", required = false)List<Long> prdIdList, @RequestParam(name = "prd_cnt", defaultValue = "0")int prdCnt
    ,HttpServletRequest request){
        // 상품ID를 바탕으로 상품 정보를 가져온다.
        // "회원정보와 동일" 라디오를 클릭하면, Ajax로 로그인한 member의 name, 주소, 전화번호, 이메일 등을 가져온다.
//        System.out.println("size : "+prdIdList.size());
////        prdIdList.forEach(System.out::println);
//        System.out.println(request.getParameter("prd_id"));
//        String[] prd_ids = request.getParameterValues("prd_id");
//        System.out.println(prd_ids);
//        System.out.println(Arrays.toString(prd_ids));
        return "order/order";
    }

    //주문
    @PostMapping("/order")
    public String order(){
        Long id = 0l;
        // 재고 파악해서, 재고 부족이면 주문 눌렀을 떄, alert창 하나 띄워서, 주문완료페이지로 넘어가지 않게 하고 cart로 리다이렉트(장바구니로, 상품ID 넘겨준다.) //update하고 throw던져서 rollback
        // return "redirect:/order/cart";
        // Order 테이블과 OrderItem 테이블에 넘어온 데이터를 담는다. 단, 유효성 검사 필요, 이름, 주소, 전화번호, 이메일, 배송메시지, 입금은행, 계좌번호, 입금자명 (input -> 선택리스트로 변경할지 고려)
        return "redirect:/order/" + id + "/success";
    }

    //주문완료 페이지
    @GetMapping("/{id}/success")
    public String success(@PathVariable(name = "id", required = false) Long id){
        //주문 완료된 상품 정보와 배송 정보들을 가져온다.
        return "order/success";
    }
}

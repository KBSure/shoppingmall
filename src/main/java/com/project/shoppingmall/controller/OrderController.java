package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Cart;
import com.project.shoppingmall.domain.Product;
import com.project.shoppingmall.dto.CartInfo;
import com.project.shoppingmall.security.LoginMember;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ProductService productService;
    
    //장바구니
    @GetMapping("/cart")
    public String getCart(HttpSession session, Authentication authentication, Model model){
        // 세션에서 카트 내역 조회
        @SuppressWarnings("unchecked")
        List<CartInfo> cartList = (List<CartInfo>) session.getAttribute("cartList");
        
        //TODO 새로고침시 중복으로 저장 방지 필요.
        // 세션에 담긴 카트리스트가 없고, 로그인 사용자이면, DB에서 조회
        if(authentication != null) {
            LoginMember loginMember = (LoginMember) authentication.getPrincipal();
            List<Cart> memberCarts = orderService.getAllMemebrCarts(loginMember.getId());
            List<Long> productIds = memberCarts.stream().map(c -> c.getProduct().getId()).collect(Collectors.toList());
            Map<Long, Product> productMap = productService.getAllProductsWithThumnail(productIds).stream().collect(Collectors.toMap(p -> p.getId(), p -> p));
            List<CartInfo> membersCartInfos = makeCartInfoList(memberCarts, productMap);
            if(cartList == null) {
                cartList = membersCartInfos;
            }
            else { // 로그인한 사용자의 세션에 담긴 카트리스트가 있으면,
                final List<CartInfo> tmpList = new ArrayList<>();
                Map<Long, CartInfo> infoMap = membersCartInfos.stream().collect(Collectors.toMap(CartInfo::getPrdId, c -> c));
                cartList.forEach(c -> {
                    // TODO 일괄 등록 하도록 수정해야댐.
                    // 카트리스트 내용
                    Cart savedCart = orderService.registCart(loginMember.getId(), c);
                    CartInfo cartInfo = infoMap.get(c.getPrdId());
                    if(cartInfo == null) {
                        c.setCartId(savedCart.getId());
                        tmpList.add(c);
                    }
                    else{
                        cartInfo.setQuantity(c.getQuantity() + cartInfo.getQuantity());
                    }
                });
                membersCartInfos.addAll(tmpList);
                cartList = membersCartInfos;
                session.removeAttribute("cartList");
            }
        }
        else if(cartList != null) {
            List<Long> productIds = cartList.stream().map(c -> c.getPrdId()).collect(Collectors.toList());
            Map<Long, Product> productMap = productService.getAllProductsWithThumnail(productIds).stream().collect(Collectors.toMap(p -> p.getId(), p -> p));
            addProductInfo(cartList, productMap);
        }
        
        model.addAttribute("cartList", cartList);
        return "order/cart";
    }
    
    private void addProductInfo(List<CartInfo> cartList, Map<Long, Product> productMap) {
        cartList.forEach(c -> {
            Product product = productMap.get(c.getPrdId());
            c.setPrice(product.getPrice());
            c.setImageId(product.getImages().get(0).getId());
            c.setProductName(product.getName());
        });
    }
    
    private List<CartInfo> makeCartInfoList(List<Cart> memberCarts, Map<Long, Product> productMap) {
        List<CartInfo> cartList = new ArrayList<>();
        memberCarts.forEach(cart -> {
            Product product = productMap.get(cart.getProduct().getId());
            if(product == null) throw new IllegalArgumentException("존재하지 않는 상품입니다.");
            CartInfo cartInfo = makeCartInfo(cart, product);
            cartList.add(cartInfo);
        });
        return cartList;
    }
    
    private CartInfo makeCartInfo(Cart cart, Product product) {
        CartInfo cartInfo = new CartInfo();
        cartInfo.setCartId(cart.getId());
        cartInfo.setPrdId(product.getId());
        cartInfo.setQuantity(cart.getQuantity());
        cartInfo.setImageId(product.getImages().get(0).getId());
        cartInfo.setProductName(product.getName());
        cartInfo.setPrice(product.getPrice());
        return cartInfo;
    }
    
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

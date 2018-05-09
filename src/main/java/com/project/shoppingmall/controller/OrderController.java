package com.project.shoppingmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    //장바구니
    @GetMapping("/cart")
    public String getCart(@RequestParam(name = "prd_cate", required = false)String prdCate, @RequestParam(name = "page", defaultValue = "1")int page,
                       @RequestParam(name = "prd_id", required = false)Long prdId, @RequestParam(name = "prd_cnt", defaultValue = "0")int prdCnt){
        // DB에서 로그인한 member의 cart내 정보를 조회한다.
        return "/order/cart";
    }

    @DeleteMapping("/cart")
    public String deleteCart(@RequestParam(name = "prd_cate", required = false)String prdCate, @RequestParam(name = "page", defaultValue = "1")int page,
                       @RequestParam(name = "prd_id", required = false)Long prdId, @RequestParam(name = "prd_cnt", defaultValue = "0")int prdCnt){
        // DB에서 해당 데이터 삭제
        return "redirect:/order/cart";
    }

    //주문페이지
    @GetMapping("/order")
    public String orderForm(@RequestParam(name = "prd_id", required = false)Long prdId, @RequestParam(name = "prd_cnt", defaultValue = "0")int prdCnt){
        // 상품ID를 바탕으로 상품 정보를 가져온다.
        // "회원정보와 동일" 라디오를 클릭하면, Ajax로 로그인한 member의 name, 주소, 전화번호, 이메일 등을 가져온다.
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

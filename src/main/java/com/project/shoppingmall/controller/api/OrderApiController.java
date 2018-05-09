package com.project.shoppingmall.controller.api;

import jdk.net.SocketFlow;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderApiController {
    //이동할 url이나 등록을 성공한 객체(DTO)를 넘겨준다.
    @PostMapping("/cart")
    public ResponseEntity<String> postCart(){
        String cartUrl = "/order/cart";

        return new ResponseEntity<String>(cartUrl, HttpStatus.OK);
    }
}

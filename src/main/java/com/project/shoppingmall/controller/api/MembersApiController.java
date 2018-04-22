package com.project.shoppingmall.controller.api;

import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MembersApiController {
    @Autowired
    MembersService service;

}

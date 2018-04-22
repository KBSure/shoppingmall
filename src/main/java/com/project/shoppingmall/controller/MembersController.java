package com.project.shoppingmall.controller;

import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MembersController {
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping("/login")
	public String loginForm() {
		
		return "members/login";
	}
	
	@GetMapping("/join")
    public String joinForm() {
	    
	    return "members/join";
    }
	
    @PostMapping("/join")
    public String join() {
	    
        return null;
    }
    
}

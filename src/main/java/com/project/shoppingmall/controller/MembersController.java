package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Role;
import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/members")
public class MembersController {
	
	@Autowired
	private MembersService membersService;
	
	@GetMapping("/login")
	public String loginForm() {
		return "members/login";
	}
	@GetMapping("/main")
	public String mainForm() {
		return "members/main";
	}
	@GetMapping("/error")
	public String errorForm() {
		return "members/error";
	}
	@GetMapping("/join")
    public String joinForm(ModelMap modelMap) {
		Member member = new Member();
		modelMap.addAttribute("member", member);//why?
	    return "members/join";
    }

	@PostMapping(path = "join")
	public String join(@ModelAttribute Member member){

		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		member.setPasswd(passwordEncoder.encode(member.getPasswd())); //사용자가 입력한 패스워드를 암호화 해서 저장

		List<Role> roleList = new ArrayList<>();
		Role userRole = new Role();
		userRole.setName("USER");
		roleList.add(userRole);
		member.setRoles(roleList);

		System.out.println("encode password : " + member.getPasswd());
		Member saveMember = membersService.addMembers(member);

		System.out.println(saveMember.getEmail());
		return "redirect:/members/main";
	}
    
}

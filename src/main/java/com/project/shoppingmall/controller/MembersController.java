package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Role;
import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/join")
    public String joinForm( ) {
		return "members/join";
    }

	@PostMapping(path = "join")
	public String join(@ModelAttribute Member member){ //Thymeleaf이용시 서버로 전달값 ModelAttribute로 받음

		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		member.setPasswd(passwordEncoder.encode(member.getPasswd()));

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

	@GetMapping(path="/findid")
	public String findIdForm()
	{
		return "members/find_id";
	}

	@PostMapping(path="/findid")
	public String findId()
	{
		return "redirect:/result";
	}

	@GetMapping(path="/findpwd")
	public String findPasswdForm()
	{
		return "members/find_passwd";
	}

	@PostMapping(path="/findpwd")
	public String findPasswd()
	{
		return "redirect:/result";
	}

	@GetMapping(path="/update/{id}")
	public String updateAccountForm(@PathVariable("id") long id)
	{
		return "members/account";
	}

	@PutMapping(path="/update/{id}")
	public String updateAccount(@PathVariable("id") long id)
	{
		return "members/account";
	}

	@DeleteMapping(path="/update/{id}")
	public String dropOut(@PathVariable("id") long id)
	{
		return "main/main";
	}

	@GetMapping(path="/update/{id}/passwd")
	public String updatePasswdForm(@PathVariable("id") long id)
	{
		return "members/passwd";
	}

	@PutMapping(path="/update/{id}/passwd")
	public String updatePasswd(@PathVariable("id") long id)
	{
		 return "members/passwd";
	}
}

package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping(path = "join")
	public String join(@ModelAttribute Member member){

//		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		member.setPasswd(passwordEncoder.encode(member.getPasswd()));
//
//		List<Role> roleList = new ArrayList<>();
//		Role userRole = new Role();
//		userRole.setName("USER");
//		roleList.add(userRole);
//		member.setRoles(roleList);
//
//		System.out.println("encode password : " + member.getPasswd());
//		Member saveMember = membersService.addMembers(member);
//
//		System.out.println(saveMember.getEmail());
		return "redirect:/members/main";
	}

	@GetMapping(path="/findid") public String findIdForm() {
		return "members/find_id";
	}

	@PostMapping(path="/findid")
	public String findId() {
		return "redirect:/result";
	}

	@GetMapping(path="/findpwd")
	public String findPasswdForm() {
		return "find_password";
	}

	@PostMapping(path="/findpwd")
	public String findPasswd() {
		return "redirect:/result";
	}

	@GetMapping(path="/update/{id}")
	public String updateAccountForm(@PathVariable("id") long id) {
		return "members/update_member";
	}

	@PutMapping(path="/update/{id}")
	public String updateAccount(@PathVariable("id") long id) {
		return "members/account";
	}

	@DeleteMapping(path="/update/{id}")
	public String dropOut(@PathVariable("id") long id) {
		return "main/main";
	}

	@GetMapping(path="/update/{id}/passwd")
	public String updatePasswdForm(@PathVariable("id") long id) {
		return "password";
	}

	@PutMapping(path="/update/{id}/passwd")
	public String updatePasswd(@PathVariable("id") long id) {
		 return "password";
	}
}

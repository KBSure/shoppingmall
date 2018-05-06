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
	
	@GetMapping("/signin")
	public String signinForm() {
		return "members/signin";
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
		return "members/find_password";
	}

	@PostMapping(path="/findpwd")
	public String findPasswd() {
		return "redirect:/result";
	}

	@GetMapping(path="/update/{id}/account")
	public String updateAccountForm(@PathVariable("id") long id) {
		return "members/update_member";
	}

	@GetMapping(path="/update/{id}")
	public String contractList(@PathVariable("id") long id) {
		return "members/contract_list";
	}

	@GetMapping(path="/update/{id}/dropout")
	public String dropOutForm(@PathVariable("id") long id) {
		return "members/dropout";
	}

	@DeleteMapping(path="/update/{id}/dropout") //메인으로 리다이렉트..
	public String dropOut(@PathVariable("id") long id) {
		return "members";
	}

	@GetMapping(path="/update/{id}/password")
	public String updatePasswdForm(@PathVariable("id") long id) {
		return "members/update_password";
	}

	@PutMapping(path="/update/{id}/password")
	public String updatePasswd(@PathVariable("id") long id) {
		 return "password";
	}

}

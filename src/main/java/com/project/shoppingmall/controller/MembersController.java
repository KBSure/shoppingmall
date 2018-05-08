package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.Role;
import com.project.shoppingmall.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

	@PostMapping("/join")
	public String join(@ModelAttribute Member member){
		//유효성 검사
			//if(유효성 검사에 에러가 있음)
				//return "members/join"
			//if(패스워드 != 패스워드)
				//bindingResult.error


		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		member.setPasswd(passwordEncoder.encode(member.getPasswd()));

		List<Role> roleList = new ArrayList<>();
		Role userRole = new Role();
		userRole.setName("USER");
		roleList.add(userRole);
		member.setRoles(roleList);

		Member saveMember = membersService.addMembers(member);

		return "redirect:/main/main";
	}

	@GetMapping(path="/findid") public String findIdForm() {
		return "members/find_id";
	}

	@PostMapping(path="/findid")
	public String findId() {
		return "members/find_result";
	}

	@GetMapping(path="/findpassword")
	public String findPasswdForm() {
		return "members/find_password";
	}

	@PostMapping(path="/findpassword")
	public String findPasswd() {
		return "members/find_result";
	}

	@GetMapping(path="/{id}/update")
	public String updateAccountForm(@PathVariable("id") long id) {
		return "members/update_member";
	}
	@PutMapping(path="/{id}")
	public String updateAccount()
	{
		return "members/update_member";
	}

	@GetMapping(path="/{id}")
	public String contractList(@PathVariable("id") long id) {
		return "members/contract_list";
	}

	@GetMapping(path="/{id}/dropout")
	public String dropOutForm(@PathVariable("id") long id) {
		return "members/dropout";
	}

	@DeleteMapping(path="/{id}") //메인으로 리다이렉트..
	public String dropOut(@PathVariable("id") long id) {
		return "members";
	}

	@GetMapping(path="/{id}/password")
	public String updatePasswdForm(@PathVariable("id") long id) {
		return "members/update_password";
	}

	@PutMapping(path="/{id}/password")
	public String updatePasswd(@PathVariable("id") long id) {
		 return "members/update_password";
	}

}

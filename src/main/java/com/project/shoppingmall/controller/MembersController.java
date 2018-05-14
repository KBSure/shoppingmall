package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Address;
import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.dto.MemberFormDTO;
import com.project.shoppingmall.dto.PasswordFormDTO;
import com.project.shoppingmall.dto.UpdateFormDTO;
import com.project.shoppingmall.service.MembersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/members")
public class MembersController {

	@Autowired
	private MembersService membersService;

	@GetMapping("/signin")
	public String signinForm(HttpServletRequest request) {
		String referer = request.getHeader(HttpHeaders.REFERER);
		
		request.getSession().setAttribute("referer", referer);
		
		return "members/signin";
	}

	@GetMapping("/join")
	public String joinForm(MemberFormDTO memberFormDTO) {
		return "members/join";
    }

	@PostMapping("/join")
	public String join(@Valid MemberFormDTO memberFormDTO, BindingResult bindingResult){

		if(bindingResult.hasErrors()) {
			return "members/join";
		}
		Member member = membersService.getUserByEmail(memberFormDTO.getEmail());
		if(member != null){
			bindingResult.addError(new FieldError("memberFormDTO","email","이미 존재하는 이메일 입니다."));
			return "members/join";
		}
		if(!memberFormDTO.getPassword().equals(memberFormDTO.getRePassword())){
			bindingResult.addError(new FieldError("memberFormDTO","password","패스워드 확인 값과 다릅니다."));
			return "members/join";
		}
		membersService.joinMembers(memberFormDTO);

		return "redirect:/members/signin";
	}

	//@GetMapping
	@GetMapping(path = "{id}")
	public String contractList(@PathVariable long id, ModelMap modelMap) {
		modelMap.addAttribute("id",id);
		//페이지 리스트 = 주문자ID,페이지정보,배송정보,날짜를 파라미터로 주문목록 리스트 가져오기
		//페이져 초기화
		//
		//.attribute(페이지리스트)
		//.attribute(페이져)
		return "members/contract_list";
	}

	@GetMapping(path="/{id}/update")
	public String updateAccountForm(@PathVariable long id, Principal principal, ModelMap modelMap) {
		Member member = membersService.getUserByEmail(principal.getName());
		UpdateFormDTO updateFormDTO = new UpdateFormDTO();
		BeanUtils.copyProperties(member, updateFormDTO);
		Address address = member.getAddress();
		updateFormDTO.setAdderss(address.getPhone(),address.getZipcode(),address.getLocation(),address.getDetail());

		modelMap.addAttribute("id",id);
		modelMap.addAttribute("updateFormDTO", updateFormDTO);
		return "members/update_member";
	}
	@PostMapping(path="/{id}/update")
	public String updateAccount(@PathVariable long id, @Valid UpdateFormDTO updateFormDTO, BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()){
			return"members/update_member";
		}

		membersService.updateMember(updateFormDTO);

		return "members/update_member";
	}

	@GetMapping(path="/{id}/password")
	public String updatePasswdForm(@PathVariable long id,PasswordFormDTO passwordFormDTO, ModelMap modelMap) {
		modelMap.addAttribute("id",id);
		return "members/update_password";
	}

	@PostMapping(path="/{id}/password")
	public String updatePasswd(@PathVariable long id, @Valid PasswordFormDTO passwordFormDTO, BindingResult bindingResult, Principal principal) {

		if(bindingResult.hasErrors()){
			return "members/update_password";
		}
		if(!passwordFormDTO.getPassword().equals(passwordFormDTO.getRePassword())){
			bindingResult.addError(new FieldError("passwordFormDTO","password","패스워드 확인 값과 다릅니다."));
			return "members/update_password";
		}
		passwordFormDTO.setEmail(principal.getName());
		membersService.updateMemberPassword(passwordFormDTO);

		return "members/update_password";
	}

	@GetMapping(path="/findid")
	public String findIdForm() {
		return "members/find_id";
	}

	@PostMapping(path="/findid")
	public String findId() {
		//form에서 사용자이름과 전화번호 정보 받아오기
		//List<Member> = Ropository에서 사용자 이름과 전화번호로 ID 받아오기
		//사용자가 있음
			//.attribute(첫번째)
		//사용자가 없음
			//bindingResult.addError
		return "members/find_result";
	}

	@GetMapping(path="/findpassword")
	public String findPasswdForm() {
		return "members/find_password";
	}

	@PostMapping(path="/findpassword")
	public String findPasswd() {
		//form에서 이메일과 전화번호 정보 받아오기
		//List<Member> = Ropository에서 사용자 이름과 전화번호로 password 받아오기
		//if(사용자가 있음)
			//.attribute(첫번째)
		//else(사용자가 없음)
			//bindingResult.addError
		return "members/find_result";
	}

	@GetMapping(path="/{id}/dropout")
	public String dropOutForm(@PathVariable long id,ModelMap modelMap) {
		modelMap.addAttribute("id",id);
		return "members/dropout";
	}

	@DeleteMapping(path="/{id}")
	public String dropOut(@PathVariable long id) {
		//form에서 password가져오기
		//Repository에서 Email로 Password 받아옴
		//if(패스워드 != 쿼리 패스워드)
			//bindingResult.addError
			//return dropout
		//int = delete한 결과값 받아오기
		//if(결과 == delete실패)
			//bindingResult.addError
		return "members";
	}



}

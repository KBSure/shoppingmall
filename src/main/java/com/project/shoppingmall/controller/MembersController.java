package com.project.shoppingmall.controller;

import com.project.shoppingmall.common.Pagination;
import com.project.shoppingmall.domain.*;
import com.project.shoppingmall.dto.MemberFormDTO;
import com.project.shoppingmall.dto.OrderInfo;
import com.project.shoppingmall.dto.PasswordFormDTO;
import com.project.shoppingmall.dto.UpdateFormDTO;
import com.project.shoppingmall.service.MembersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//진행중
@Slf4j
@Controller
@RequestMapping("/members")
public class MembersController {

	@Autowired
	private MembersService membersService;

	@GetMapping("/signin")
	public String signinForm(HttpServletRequest request,@RequestParam(defaultValue = "0")int error,ModelMap modelMap) {

		if(error == 1){
			modelMap.addAttribute("error","drop");
		}

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
	@GetMapping(path ="{id}")
	public String contractList(@PathVariable long id,
							   @RequestParam(defaultValue = "1")int page,
							   @RequestParam(name = "state",required = false,defaultValue="DEFAULT")String deliveryState,
							   Principal principal,
							   ModelMap modelMap) {
		modelMap.addAttribute("id",id);

		//페이지  = 주문자정보,페이지번호,배송상태정보
		Member member = membersService.getUserByEmail(principal.getName());
		Page<Order> orders = membersService.getOrderList(member,page,DeliveryState.valueOf(deliveryState));
		List<Order> orderList = orders.getContent();
		List<OrderInfo> orderInfos = new ArrayList<>();

		for(Order order: orderList){
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setProductName(order.getOrderItems().get(0).getProductName());
			orderInfo.setPrice(order.getOrderItems().get(0).getProductPrice());
			orderInfo.setDeliveryState(order.getDeliveryState());
			orderInfo.setImageId(order.getOrderItems().get(0).getProduct().getId());
			orderInfo.setRegDate(order.getRegDate());
			orderInfos.add(orderInfo);
		}


		//페이져 초기화
		Pagination pagination = new Pagination(orders.getTotalElements(),orders.getTotalPages(),page,5);

		modelMap.addAttribute("orders",orderInfos);
		modelMap.addAttribute("pagination",pagination);

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
	public String updatePasswordForm(@PathVariable long id,PasswordFormDTO passwordFormDTO, ModelMap modelMap) {
		modelMap.addAttribute("id",id);
		return "members/update_password";
	}

	@PostMapping(path="/{id}/password")
	public String updatePassword(@PathVariable long id, @Valid PasswordFormDTO passwordFormDTO, BindingResult bindingResult, Principal principal) {

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
	public String findPasswordForm() {
		return "members/find_password";
	}

	@PostMapping(path="/findpassword")
	public String findPassword() {
		//form에서 이메일과 전화번호 정보 받아오기
		//List<Member> = Ropository에서 사용자 이름과 전화번호로 password 받아오기
		//if(사용자가 있음)
		//.attribute(첫번째)
		//else(사용자가 없음)
		//bindingResult.addError
		return "members/find_result";
	}

	@GetMapping(path="/{id}/dropout")
	public String dropOutForm(@PathVariable long id,PasswordFormDTO passwordForm,ModelMap modelMap) {
		modelMap.addAttribute("id",id);
		return "members/dropout";
	}

	@PostMapping(path="/{id}/dropout")
	public String dropOut(@PathVariable long id,PasswordFormDTO passwordForm,BindingResult bindingResult,Principal principal) {
	    Member member = membersService.getUserByEmail(principal.getName());
		PasswordEncoder passwordEncoder = getCustomDelegatingPasswordEncoder("noop");

		if(member.getPassword().contains("bcrypt")){
			passwordEncoder = getCustomDelegatingPasswordEncoder("bcrypt");
		}else if(member.getPassword().contains("scrypt")){
			passwordEncoder = getCustomDelegatingPasswordEncoder("scrypt");
		}
	    passwordEncoder.encode(passwordForm.getPassword());
	    if(!passwordEncoder.matches(passwordForm.getPassword(),member.getPassword())){
	    	bindingResult.addError(new FieldError("passwordFormDTO","password","패스워드가 일치 하지 않습니다."));
	    	return "members/dropout";
		}
		membersService.dropout(member);
		return "redirect:/logout";
	}

	PasswordEncoder getCustomDelegatingPasswordEncoder(String idForEncode){
		Map encoders = new HashMap<>();
		encoders.put("bcrypt",new BCryptPasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		encoders.put("scrypt", new SCryptPasswordEncoder());

		return new DelegatingPasswordEncoder(idForEncode,encoders);
	}
}

package com.project.shoppingmall.config;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.dto.SigninPram;
import com.project.shoppingmall.service.LoginMember;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        LoginMember loginMember = (LoginMember)authentication.getPrincipal();
        request.getSession().setAttribute("member",new SigninPram(loginMember.getId(),loginMember.getName()));
        super.onAuthenticationSuccess(request, response, authentication);

    }
}

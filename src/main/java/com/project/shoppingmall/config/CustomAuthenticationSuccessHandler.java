package com.project.shoppingmall.config;

import com.project.shoppingmall.dto.SigninParam;
import com.project.shoppingmall.security.LoginMember;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        LoginMember loginMember = (LoginMember)authentication.getPrincipal();//DTO
        request.setAttribute("signinParam",new SigninParam(loginMember.getId().toString(),loginMember.getName()));
        request.getRequestDispatcher("/").forward(request,response);

       // request.setAttribute("memberId",loginMember.getId());
      //  request.setAttribute("name",loginMember.getName());

        //TODO
        //Referer처리하기
        super.onAuthenticationSuccess(request, response, authentication);

    }
}

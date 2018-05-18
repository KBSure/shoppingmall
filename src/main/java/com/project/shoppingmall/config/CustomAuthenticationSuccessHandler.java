package com.project.shoppingmall.config;

import com.project.shoppingmall.domain.Member;
import com.project.shoppingmall.domain.MemberStat;
import com.project.shoppingmall.dto.SigninPram;
import com.project.shoppingmall.security.LoginMember;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//진행중
@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    CustomAuthenticationSuccessHandler() {
        setUseReferer(true);
        setRedirectStrategy(new CustomRedirectStrategy());
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        LoginMember loginMember = (LoginMember)authentication.getPrincipal();

        if(loginMember.getMemberStat().equals(MemberStat.DROPOUT)) {
            new SecurityContextLogoutHandler().logout(request,response,authentication);
            response.sendRedirect("/members/signin?error=1");
            log.info("탈퇴한 사용자 입니다.");
        }
        else{

            request.getSession().removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }



    private static class CustomRedirectStrategy extends DefaultRedirectStrategy {
        @Override
        public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
            String referer = (String) request.getSession().getAttribute("referer");
            if(referer != null) {
                url = referer;
                request.getSession().removeAttribute("referer");
            }
            super.sendRedirect(request, response, url);
        }
    }
}

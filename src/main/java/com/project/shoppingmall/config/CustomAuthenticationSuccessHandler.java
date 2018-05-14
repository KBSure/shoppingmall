package com.project.shoppingmall.config;

import com.project.shoppingmall.dto.SigninPram;
import com.project.shoppingmall.security.LoginMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    CustomAuthenticationSuccessHandler() {
        setUseReferer(true);
        setRedirectStrategy(new CustomRedirectStrategy());
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        LoginMember loginMember = (LoginMember)authentication.getPrincipal();
        request.getSession().setAttribute("member",new SigninPram(loginMember.getId(),loginMember.getName()));
        
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

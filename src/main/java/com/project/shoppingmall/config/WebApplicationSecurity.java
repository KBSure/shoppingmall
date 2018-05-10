package com.project.shoppingmall.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class WebApplicationSecurity  extends WebSecurityConfigurerAdapter {

    //TODO
    //세션관리작성예정
        //세션유효시간설정
        //다중로그인제어
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and().authorizeRequests()
                .antMatchers("/members/**").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin()
                .loginPage("/members/signin").usernameParameter("email").passwordParameter("password")
                .failureUrl("/main/error")
                .defaultSuccessUrl("/", true)
                .and().logout().permitAll();
    }
}

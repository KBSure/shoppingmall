package com.project.shoppingmall.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class WebApplicationSecurity  extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .requestMatchers(new AntPathRequestMatcher("/static/**"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/boards").and()
                .authorizeRequests()
                .antMatchers("/members/**").permitAll() //일단 DB에 SELECT로 가져와서 권한 검사는 함..
                .antMatchers("/products/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/order/**").permitAll()
                .and().formLogin()
                .loginPage("/members/signin").usernameParameter("email").passwordParameter("password")//이게 없으면 스프링 기본 폼 사용됨
                .failureUrl("/main/error")
                .defaultSuccessUrl("/", true)
                .and().logout().permitAll()
                .and().csrf().disable();
    }
}

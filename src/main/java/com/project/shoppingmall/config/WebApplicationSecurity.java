package com.project.shoppingmall.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class WebApplicationSecurity  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/boards").and()
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/**.html")).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .antMatchers("/members/**").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin().loginPage("/members/signin").usernameParameter("email").passwordParameter("passwd")
                .failureUrl("/members/error")
                .defaultSuccessUrl("/members/main", true)
                .and().logout().permitAll();
    }
}

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
                .requestMatchers(new AntPathRequestMatcher("/static/**"))
                .requestMatchers(new AntPathRequestMatcher("/image/**"));
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .and().authorizeRequests()
                .antMatchers("/members/signin").permitAll()
                .antMatchers("/members/join").permitAll()
                .antMatchers("/products/**").permitAll()
                .antMatchers("/order/cart").permitAll()
                .antMatchers("/api/order/**").permitAll()
                .antMatchers("/api/wishlist/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/h2-console/**").permitAll()//여기서부터
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringAntMatchers("/**")
                .and().headers().frameOptions().disable()//여기까지 h2설정
                .and().formLogin()
                    .loginPage("/members/signin")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .successHandler(new CustomAuthenticationSuccessHandler())
                    .failureUrl("/members/signin")
//                    .defaultSuccessUrl("/", true)
                .and().logout().permitAll();
    }
}

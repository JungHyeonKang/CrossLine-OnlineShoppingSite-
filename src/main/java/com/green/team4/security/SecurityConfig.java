package com.green.team4.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public LoginFailHandler loginFailHandler(){
        return new LoginFailHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/account/login", "/account/signup", "/shop/list", "/mainPage","bs/board/list").permitAll()
                .antMatchers("/account/login/**").authenticated()
                .antMatchers("/mypage/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                .antMatchers("/order/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MEMBER')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("id")
                .loginPage("/account/login")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/mainPage")
                .failureHandler(loginFailHandler())
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .logoutSuccessUrl("/mainPage")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().accessDeniedPage("/account/denied");
    }
}

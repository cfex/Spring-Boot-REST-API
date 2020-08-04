package com.restapi.bookstore.security.config;

import com.restapi.bookstore.security.impl.UserPrincipalDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@AllArgsConstructor
@EnableWebSecurity
@EnableSwagger2
@Configuration
public class WebAppConfig extends WebSecurityConfigurerAdapter {

    private final UserPrincipalDetailsServiceImpl userPrincipalDetailsServiceImpl;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/books/**", "/users/listAll", "/h2/**").permitAll()
                .antMatchers("/users/profile").authenticated()
                .antMatchers("/users/admin/**").hasRole("ADMIN")
                .antMatchers("/books/save", "/books/deleteById", "/books/updateById").authenticated()
                .and()
                .httpBasic();
        http.headers().frameOptions().disable();
    }

    public void config(AuthenticationManagerBuilder authenticationManager) throws Exception {
        authenticationManager.userDetailsService(userPrincipalDetailsServiceImpl)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

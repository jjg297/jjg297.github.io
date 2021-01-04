package com.jgar730.Spotitube.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Commented this part out as it seemed to cause a login window to pop-up, which we dont want
//        http.cors().disable()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers(HttpMethod.PUT, "/**").permitAll()
//                .anyRequest()
//                .fullyAuthenticated()
//                .and()
//                .httpBasic()
//                .and()
//                .csrf().disable();

        http.cors().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/**").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

}

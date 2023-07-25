package com.hu.vbm672.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    // WebSecurityConfigurerAdapter deprecated, this is the way ;)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(new OrRequestMatcher(
                        new AntPathRequestMatcher("/"),
                        new AntPathRequestMatcher("/signup"),
                        new AntPathRequestMatcher("/images/**") ) )
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/welcome") // Redirect to "/welcome" after successful login
                .and()
                .logout()
                .logoutUrl("/logout") // Specify the URL for the logout action
                .logoutSuccessUrl("/").permitAll();
        return http.build();
    }
}

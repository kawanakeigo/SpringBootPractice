package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/signup", "/admin/signin", "/css/**", "/js/**").permitAll() // 誰でもOK
                .anyRequest().authenticated() // それ以外は認証必要
            )
            .formLogin(form -> form
                .loginPage("/admin/signin") // ログインページの指定
                .defaultSuccessUrl("/admin/contacts", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/admin/signin")
            )
            .csrf(csrf -> csrf.disable()); 

        return http.build();
    }
}

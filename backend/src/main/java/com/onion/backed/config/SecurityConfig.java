package com.onion.backed.config;

import com.onion.backed.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomerUserDetailsService userDetailsService;

    // HTTP 요청에 대한 보안 설정
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        // Swagger UI 경로 허용
                        .requestMatchers(
                                "/swagger-ui/**",      // Swagger UI 리소스
                                "/v3/api-docs/**",     // API 문서 경로
                                "/api/users/signUp",     // Sign Up page
                                "/api/users/login"     // Sign Up page
                        ).permitAll()
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                );
                //.formLogin(Customizer.withDefaults());
        //.logout(logout -> logout.permitAll());
        return http.build();
    }
        // 신규

        @Bean
        public PasswordEncoder passwordEncoder () {
            return new BCryptPasswordEncoder();

    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();

    }
}





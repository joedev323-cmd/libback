package com.example.libback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Allow public access to static assets, landing page, and catalogue
                .requestMatchers("/css/**", "/js/**", "/img/**", "/", "/index", "/catalog/search","/admin/catalog/add","/admin/add/member","/catalog//accessions/batch","admin/register-borrower","/Catalogue/edit/**", "/Catalogue/update/**","/h2/**").permitAll()
                // Everything else (dashboard, circulation, etc.) requires authentication
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")             // Maps to your login.html controller route
                .loginProcessingUrl("/login")     // The POST action URL in your login form
                .defaultSuccessUrl("/dashboard", true) // Where to go after success
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .permitAll()
            );
            
        return http.build();
    }
}
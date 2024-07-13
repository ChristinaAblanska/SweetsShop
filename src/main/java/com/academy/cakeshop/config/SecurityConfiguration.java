package com.academy.cakeshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Properties;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize ->
//                        authorize.anyRequest()
//                                .permitAll())
//                .csrf(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }
    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //DEFINE PUBLIC ENDPOINTS IF ANY
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole("STORE", "MALL", "SUPPLIER", "MANAGER", "EMPLOYEE", "CLIENT", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/users/**").hasAnyRole("ADMIN", "SUPPLIER")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/bankAccounts/**").hasAnyRole("STORE", "MALL", "SUPPLIER", "MANAGER", "EMPLOYEE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/bankAccounts/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/bankAccounts/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/contracts/**").hasAnyRole("STORE", "MALL", "SUPPLIER", "MANAGER", "EMPLOYEE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/contracts/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/contracts/**").hasAnyRole("ADMIN", "STORE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/contracts/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/payments/**").hasAnyRole("ADMIN", "STORE")
                        .requestMatchers(HttpMethod.POST, "/api/v1/payments/**").hasAnyRole("ADMIN", "STORE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/payments/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/accountHistory/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/accountHistory/**").hasAnyRole("ADMIN", "STORE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/accountHistory/**").hasRole("ADMIN")
                        //ALL THE REST AUTHENTICATION REQUIRED
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("nhristeva.dev@gmail.com");
        mailSender.setPassword("D3vm@il1");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
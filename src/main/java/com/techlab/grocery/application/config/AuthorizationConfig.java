package com.techlab.grocery.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;


/**
 * The AuthorizationConfig class
 * <p>
 * This class is used to configure the authorization for the application.
 */
@Configuration
public class AuthorizationConfig {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    Md5PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers("v1/admin/**").hasRole("ADMIN")
                                .requestMatchers("v1/user/**").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .formLogin(in -> in.permitAll())
                .logout(out -> out.permitAll())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}

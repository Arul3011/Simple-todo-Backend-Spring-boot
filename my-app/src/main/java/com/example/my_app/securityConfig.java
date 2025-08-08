// package com.example.my_app;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class securityConfig {

//     @Bean
//     public SecurityFilterChain filterReq(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()
//             .authorizeHttpRequests(auth -> auth
//                 .anyRequest().authenticated()
//             )
//             .formLogin()  // Optional: adds a default login page
//             .and()
//             .httpBasic(); // Optional: enables Basic Auth
//         return http.build();
//     }
// }



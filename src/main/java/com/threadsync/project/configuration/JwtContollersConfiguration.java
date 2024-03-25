// package com.threadsync.project.configuration;


// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// // import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;

// @Configuration
// // @EnableWebSecurity
// public class JwtContollersConfiguration{

//     public void configure(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//             .authorizeRequests()
//             .requestMatchers("/login", "/register", "/list").permitAll() // Exclude login and token endpoints from authentication
//             .anyRequest().authenticated();
//     }

//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                 registry.addMapping("/**").allowedOrigins("http://localhost:5173");
//             }
//         };
//     }
// }

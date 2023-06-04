//package com.example.javabackend.admin.config;
//
//import com.example.javabackend.modules.user.service.CustomUserDetailService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new CustomUserDetailService();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(userDetailsService());
//        auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
//            Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers( "/css/**", "/js/**", "/", "/register", "/error")
//                        .permitAll()
//                        .requestMatchers( "/books/edit", "/books/delete")
//                        .hasAnyAuthority("Admin")
//                        .requestMatchers("/books", "/themdanhmuc")
//                        .hasAnyAuthority("Admin","User")
//                        .requestMatchers("/api/**")
//                        .hasAnyAuthority("Admin","User")
//                        .anyRequest().authenticated()
//                )
//                .logout(logout -> logout.logoutUrl("/logout")
//                        .logoutSuccessUrl("/login")
//                        .deleteCookies("JSESSIONID")
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .permitAll()
//                )
//                .formLogin(formLogin -> formLogin.loginPage("/login")
//                        .loginProcessingUrl("/indexSB")
//                        .defaultSuccessUrl("/")
//                        .permitAll()
//                )
//                .rememberMe(rememberMe -> rememberMe.key("uniqueAndSecret")
//                        .key("my-remember-me-key") // Key để mã hóa cookie
//                        .rememberMeParameter("remember-me") // Tên của tham số để nhận biết cookie
//                        .tokenValiditySeconds(86400) // Thời gian sống của cookie (tính bằng giây)
//                        .userDetailsService(userDetailsService()) // UserDetailsService để lấy thông tin người dùng
//                )
//
//                .exceptionHandling(exceptionHandling ->
//                        exceptionHandling.accessDeniedPage("/403"))
//                .build();
//    }
//}
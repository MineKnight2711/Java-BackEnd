package com.example.javabackend.admin.config;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.user.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AccountRepository accountRepository;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .dispatcherTypeMatchers(HttpMethod.valueOf("/login")).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .permitAll();
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(email -> {
            Accounts account = accountRepository.findByEmail(email);
            if (account != null) {
                return new User(account.getEmail(), account.getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("Tài khoản không tồn tại");
            }
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
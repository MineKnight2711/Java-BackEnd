package com.example.javabackend.entity;

import com.example.javabackend.modules.account.repository.IAccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails. UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetail implements UserDetails {
    private final Accounts accounts;
    private final IAccountRepository userRepository;
    public CustomUserDetail (Accounts accounts,IAccountRepository userRepository) {
        this.accounts = accounts;
        this.userRepository=userRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        String[] roles = userRepository.getRolesOfUser(accounts.getAccountID());
        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority (role));
        }
        return authorities;
    }



    @Override
    public String getPassword() {
        return accounts.getPassword();
    }

    @Override
    public String getUsername() {
        return accounts.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


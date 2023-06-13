package com.example.javabackend.modules.user.service;


import com.example.javabackend.entity.Accounts;
import com.example.javabackend.entity.CustomUserDetail;

import com.example.javabackend.modules.user.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService{

    @Autowired
    private IAccountRepository userRepository;
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        System.out.println("Email recived :"+username);
        Accounts user = userRepository.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return new CustomUserDetail(user, userRepository);
    }
}



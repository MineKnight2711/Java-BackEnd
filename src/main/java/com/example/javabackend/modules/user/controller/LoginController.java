package com.example.javabackend.modules.user.controller;

import com.example.javabackend.entity.Accounts;

import com.example.javabackend.modules.user.DTO.AccountsDTO;
import com.example.javabackend.modules.user.DTO.UserLoginDto;
import com.example.javabackend.modules.user.repository.IAccountRepository;
import com.example.javabackend.modules.user.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String dangNhap(Model m){
        return "/login/index";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   Model model) {
        UserLoginDto dto = new UserLoginDto();
        dto.setUsername(email);
        dto.setPassword(password);
        var response = this.accountService.login(dto);
        System.out.println(response.getStatus());
        if (response.getStatus() == "Success") {
            model.addAttribute("account", response.getStatus());
            return "/indexSB";
        } else {
            model.addAttribute("error", response.getStatus());
            return "/login/index";
        }
    }
}
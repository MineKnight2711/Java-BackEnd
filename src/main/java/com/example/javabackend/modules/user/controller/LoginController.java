package com.example.javabackend.modules.user.controller;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.user.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/login")
    public String dangNhap(Model m){
        return "/login/index";
    }

    @PostMapping("/login")
    public String processLoginForm(@RequestParam("email") String email,
                                   @RequestParam("password") String password,
                                   Model model) {
        Accounts account = accountRepository.findByEmail(email);
        if (account != null) {
            model.addAttribute("account", account);
            return "home";
        } else {
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng");
            return "login";
        }
    }

}
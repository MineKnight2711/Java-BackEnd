package com.example.javabackend.modules.user.controller;

import com.example.javabackend.entity.Dishes;
import com.example.javabackend.modules.user.DTO.*;
import com.example.javabackend.modules.user.service.AccountService;
import com.example.javabackend.modules.user.service.PasswordResetService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")

public class AccountController {
    @Autowired
    private AccountService accountsService;
    @Autowired
    private PasswordResetService passwordResetService;
    //Get All User
    @GetMapping("/all")
    public List<AccountsDTO> getAllAccount() {
        return accountsService.getAllAccount();
    }

    //Get By Id
    @GetMapping("id/{id}")
    public AccountsDTO getById(@PathVariable Long id) {
        return accountsService.getById(id);
    }

    //Create Account

    @PostMapping
    public AccountResponseDto createAccount(@ModelAttribute AccountsDTO accountsDTO) throws IOException {
        System.out.println(accountsDTO);
        return this.accountsService.createAccount(accountsDTO);
    }
    //Login
    @PostMapping("/login")
    public AccountResponseDto login(@RequestBody UserLoginDto user) throws Exception {
        return accountsService.login(user);
    }
    @PutMapping("/update")
    public UpdateAccountDto updateAccount(@RequestBody UpdateAccountDto accountsDTO) throws Exception {
        return accountsService.updateUser(accountsDTO);
    }
    @PutMapping("/password")
    public ChangePassDto changePass(@RequestBody ChangePassDto user) {
        return this.accountsService.changePass(user);
    }
    @PostMapping("/otp")
    public ResponseEntity<String> sendOtpToEmail(@RequestParam String email) {
        try {
            boolean result = passwordResetService.sendOtpToEmail(email);
            if(result) {
                return ResponseEntity.ok("Đã gửi otp đến hộp thư của bạn");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Không tìm thấy tài khoản");
            }
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gửi mail không thành công!");
        }
    }
    @PutMapping("/changepassword/{email}")
    public ResponseEntity<String> changePassword(
            @PathVariable("email") String email,
            @RequestParam("otpValue") String otpValue,
            @RequestParam("newPassword") String newPassword) {

        String status=passwordResetService.changePassword(email,otpValue,newPassword);

        return ResponseEntity.ok(status);
    }
}
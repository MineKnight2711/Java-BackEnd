package com.example.javabackend.modules.account.service;

import com.example.javabackend.entity.Accounts;
import com.example.javabackend.modules.account.DTO.ChangePassDto;
import com.example.javabackend.modules.account.DTO.otpDTO;
import com.example.javabackend.modules.account.repository.IAccountRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Service
public class PasswordResetService {

    @Autowired
    private JavaMailSender javaMailSender; // Autowire JavaMailSender to send email

    @Autowired
    private IAccountRepository accountRepository; // Autowire UserRepository to fetch user details

    private static final int OTP_LENGTH = 6; // Length of the OTP
    private otpDTO otp;
    // Method to send OTP to user's email
    public boolean sendOtpToEmail(String email) throws MessagingException {

        // Tạo ngẫu nhiên OTP
        otp = generateOtp();
        long remainingMinutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), otp.getOtpExpiryTime());

        // Tiìm kiếm account dựa trên email
        Accounts accounts  = accountRepository.findByEmail(email);
        if(accounts!=null){
            //Tuỳ chỉnh các thuộc tính của template
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML");
            templateResolver.setCharacterEncoding("UTF-8");

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);
            // Set the template variables
            Context context = new Context();
            context.setVariable("name", accounts.getFullName());
            context.setVariable("otp", otp.getOtp());
            context.setVariable("otpExpiryTime", remainingMinutes);
            // Process the template
            String processedHtml = templateEngine.process("templates/otptemplate", context);

            // Send email with OTP to user's email
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("misteranonymous2711@gmail.com");
            helper.setTo(email);
            helper.setSubject("OTP for password reset");
            helper.setText(processedHtml, true);
            javaMailSender.send(message);

            return true;
        }
        return false;
    }

    // Method to generate OTP
    private otpDTO generateOtp() {
        String numbers = "0123456789";
        Random random = new Random();
        char[] otp = new char[OTP_LENGTH];
        for(int i = 0; i < OTP_LENGTH; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }

        otpDTO newotp = new otpDTO();
        newotp.setOtp(new String(otp));
        newotp.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));

        return newotp;
    }
    public String changePassword(String email,String otpValue,String newPassword){
        Accounts account = accountRepository.findByEmail(email);
        if(account == null) {
            return "User not exist";
        }
        if (otp == null || !otp.getOtp().equals(otpValue) ||
                otp.getOtpExpiryTime().isBefore(LocalDateTime.now())) {
            ChangePassDto response = new ChangePassDto();
            response.setStatus("Invalid OTP");
            return "Invalid OTP";
        }
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(newPassword, salt);
        account.setPassword(hashedPassword);
        accountRepository.save(account);
        return "Đổi mật khẩu thành công!";
    }
}

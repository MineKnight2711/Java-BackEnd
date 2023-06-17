package com.example.javabackend.admin.controller;

import com.example.javabackend.modules.user.DTO.AccountResponseDto;
import com.example.javabackend.modules.user.DTO.AccountsDTO;
import com.example.javabackend.modules.user.service.AccountService;
import com.example.javabackend.modules.user.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AccountTypeService accountTypeService;
    @Autowired
    private AccountService accountService;
    @GetMapping("/themnguoiquantri")
    public String addAdmin(Model m){
        m.addAttribute("newaccount",new AccountsDTO());
        m.addAttribute("accounttypes", accountTypeService.getAllAccountTypes());
        return "/account/index";
    }
    @PostMapping("/themnguoiquantri")
    public String createAccountWeb(@Valid @ModelAttribute("newaccount")AccountsDTO accountsDTO, BindingResult result,Model model) throws IOException {
        if (result.hasErrors()) {

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            model.addAttribute("accounttypes", accountTypeService.getAllAccountTypes());
            return "/account/index";
        }
        this.accountService.createAccount(accountsDTO);
        return "redirect:/themnguoiquantri";
    }
}

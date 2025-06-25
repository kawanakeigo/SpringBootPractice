package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminAuthController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("adminForm", new AdminForm());
        return "admin/signup";
    }
  

    @GetMapping("/signin")
    public String signinForm() {
        return "admin/signin";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute AdminForm form, Model model) {
        try {
            adminService.registerAdmin(form);
            return "redirect:/admin/signin";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/signup";
        }
    }
}
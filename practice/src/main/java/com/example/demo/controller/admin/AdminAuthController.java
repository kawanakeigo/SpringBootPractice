package com.example.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/signup")
    public String signup(@ModelAttribute AdminForm form) {
    	adminService.registerAdmin(form);
        return "redirect:/admin/signin";
    }

    @GetMapping("/signin")
    public String signinForm() {
        return "admin/signin";
    }

    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, Model model) {
        if (adminService.login(email, password) != null) {
            return "redirect:/admin/contacts";
        } else {
            model.addAttribute("error", "ログインに失敗しました。");
            return "admin/signin";
        }
    }
}
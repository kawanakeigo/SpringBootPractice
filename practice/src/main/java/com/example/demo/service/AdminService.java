package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;

public interface AdminService {
    void registerAdmin(AdminForm form);
    Admin login(String email, String password);
}


package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void registerAdmin(AdminForm form) {
    	if (adminRepository.findByEmail(form.getEmail()).isPresent()) {
            throw new IllegalArgumentException("このメールアドレスは既に登録されています");
        }
    	
        Admin admin = new Admin();
        admin.setLastName(form.getLastName());
        admin.setFirstName(form.getFirstName());
        admin.setEmail(form.getEmail());
        admin.setPassword(passwordEncoder.encode(form.getPassword()));
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());
        adminRepository.save(admin);
    }

    @Override
    public Admin login(String email, String password) {
        return adminRepository.findByEmail(email)
                .filter(admin -> encoder.matches(password, admin.getPassword()))
                .orElse(null);
    }
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
   
    
    
}
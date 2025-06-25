package com.example.demo.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Contact;
import com.example.demo.service.AdminService;
import com.example.demo.service.ContactService;


@Controller
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	private AdminService adminService;

    @Autowired
    private ContactService contactService;
    
    
    
   

    @GetMapping("/admin/contacts")
    public String contactList(Model model) {
        List<Contact> contacts = contactService.findAllContacts();
        model.addAttribute("contacts", contacts);
        return "admin/contactList";
    }
    
    @GetMapping("/admin/contacts/{id}")
    public String showContactDetail(@PathVariable Long id, Model model) {
        Optional<Contact> contactOpt = contactService.getContactById(id);
        if (contactOpt.isPresent()) {
            model.addAttribute("contact", contactOpt.get());
            return "admin/contactDetail";  // ← HTML名（次のステップで作成）
        } else {
            return "redirect:/admin/contacts";  // 該当IDがない場合一覧に戻す
        }
    }
        
    
    
    @GetMapping("/admin/contacts/{id}/edit")
    public String showEditContact(@PathVariable Long id, Model model) {
        Optional<Contact> contactOpt = contactService.getContactById(id);
        if (contactOpt.isPresent()) {
            model.addAttribute("contact", contactOpt.get());
            return "admin/contactEdit"; // ← 編集用HTMLがある前提
        } else {
            return "redirect:/admin/contacts";
        }
    }
}


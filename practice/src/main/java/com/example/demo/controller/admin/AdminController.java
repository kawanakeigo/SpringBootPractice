package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Contact;
import com.example.demo.service.ContactService;

@Controller
public class AdminController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/admin/contacts")
    public String contactList(Model model) {
        List<Contact> contacts = contactService.findAllContacts();
        model.addAttribute("contacts", contacts);
        return "admin/contactList";
    }
}

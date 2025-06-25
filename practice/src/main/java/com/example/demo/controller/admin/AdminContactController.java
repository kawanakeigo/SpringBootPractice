package com.example.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminContactController {
	

    @GetMapping("/admin/contacts")
    public String showContactList(Model model) {
        // contact一覧をDBから取得し、modelに追加する処理をここに書く
        // 例: model.addAttribute("contacts", contactService.findAll());

        return "admin/contactList";  // ← ここで表示するviewファイル名
    }
}

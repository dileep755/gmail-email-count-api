package com.example.gmailapi.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gmailapi.service.GmailService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/gmail")
public class GmailController {

    private final GmailService service;

    public GmailController(GmailService service) {
        this.service = service;
    }

    @GetMapping("/count")
    @ResponseBody
    public Map<String, Object> count(
            @RequestParam String loginEmail,
            @RequestParam String sender
    ) throws Exception {

        int count = service.countEmails(loginEmail, sender);

        return Map.of(
                "email", loginEmail,
                "sender", sender,
                "count", count
        );
    }
    
    @GetMapping("/home")
    public String showEmailHome(HttpSession session) {

        if (session.getAttribute("userEmail") == null)
            return "redirect:/";

        return "email-count";   // JSP name
    }
    
}

package com.example.gmailapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller

public class LoginController {
	
	@GetMapping("/")
    public String loginPage() {
       
            return "login";
    }

}

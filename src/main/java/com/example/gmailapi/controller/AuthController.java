package com.example.gmailapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.gmailapi.service.GoogleOAuthService;
import com.google.api.client.auth.oauth2.TokenResponse;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final GoogleOAuthService service;

    public AuthController(GoogleOAuthService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam String email) throws Exception {

        // 1. Gmail OAuth authorization URL generate
        String oauthUrl = service.getAuthUrl(email);

        // 2. ModelAndView me put
        ModelAndView mv = new ModelAndView("redirect-page"); // JSP name
        mv.addObject("redirectUrl", oauthUrl);

        return mv;
    }

//    @GetMapping("/callback")
//    public String callback(
//            @RequestParam String code,
//            @RequestParam String state
//    ) throws Exception {
//
//        TokenResponse token = service.getFlow()
//                .newTokenRequest(code)
//                .setRedirectUri(service.getRedirectUri())
//                .execute();
//
//        service.saveToken(state, token);
//
//        return "OAuth Success! Token saved for: " + state;
//    }
    
    
    
    @GetMapping("/callback")
    public String callback(
            @RequestParam String code,
            @RequestParam String state,
            HttpSession session
    ) throws Exception {

        TokenResponse token = service.getFlow()
                .newTokenRequest(code)
                .setRedirectUri(service.getRedirectUri())
                .execute();

        service.saveToken(state, token);

        session.setAttribute("userEmail", state);

        return "redirect:/gmail/home";
    }

}

package com.example.gmailapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gmailapi.beans.EmailCountResponse;
import com.example.gmailapi.service.GmailEmailCounterService;

@RestController
@RequestMapping("/api/gmail")
public class GmailEmailController {

    private final GmailEmailCounterService gmailEmailCounterService;

    public GmailEmailController(GmailEmailCounterService gmailEmailCounterService) {
        this.gmailEmailCounterService = gmailEmailCounterService;
    }

    @GetMapping("/count")
    public ResponseEntity<EmailCountResponse> getEmailCount(
            @RequestParam("sender") String senderEmail) {

        int count = gmailEmailCounterService.countEmailsFromSender(senderEmail);
        EmailCountResponse response = new EmailCountResponse(senderEmail, count);
        return ResponseEntity.ok(response);
    }


}

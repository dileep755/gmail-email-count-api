package com.example.gmailapi.service;

import org.springframework.stereotype.Service;

import com.example.gmailapi.util.GoogleAuthUtil;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;

@Service
public class GmailService {

    private final GoogleOAuthService oauthService;

    public GmailService(GoogleOAuthService oauthService) {
        this.oauthService = oauthService;
    }

    public int countEmails(String loginEmail, String sender) throws Exception {

        var token = oauthService.getToken(loginEmail);

        Gmail gmail = GoogleAuthUtil.buildGmailService(token);

        ListMessagesResponse response =
                gmail.users().messages().list("me").setQ("from:" + sender).execute();

        return response.getMessages() == null ? 0 : response.getMessages().size();
    }
}

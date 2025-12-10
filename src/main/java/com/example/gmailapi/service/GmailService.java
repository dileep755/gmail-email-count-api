package com.example.gmailapi.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import java.util.*;
import com.google.api.services.gmail.model.Message;

import com.example.gmailapi.util.GoogleAuthUtil;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.MessagePartHeader;

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



    
    public List<String> getEmailSubjects(String loginEmail, String sender) throws Exception {

    var token = oauthService.getToken(loginEmail);
    Gmail gmail = GoogleAuthUtil.buildGmailService(token);

    List<String> subjects = new ArrayList<>();

    // Fetch all messages from sender
    ListMessagesResponse response =
            gmail.users().messages().list("me")
                    .setQ("from:" + sender)
                    .execute();

    List<Message> messages = response.getMessages();
    if (messages == null || messages.isEmpty()) {
        return subjects; 
    }

    // Loop through each message to extract subject
    for (Message msg : messages) {

        Message fullMessage = gmail.users()
                .messages()
                .get("me", msg.getId())
                .setFormat("metadata")
                .setMetadataHeaders(List.of("Subject"))
                .execute();

        String subject = fullMessage.getPayload().getHeaders().stream()
                .filter(h -> "Subject".equalsIgnoreCase(h.getName()))
                .map(MessagePartHeader::getValue)
                .findFirst()
                .orElse("(No Subject)");

        subjects.add(subject);
    }

    return subjects;
}
}

package com.example.gmailapi.util;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.*;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.Gmail;

public class GoogleAuthUtil {

    public static Gmail buildGmailService(TokenResponse token) throws Exception {
        Credential credential = new Credential(BearerToken.authorizationHeaderAccessMethod())
                .setAccessToken(token.getAccessToken());

        return new Gmail.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                credential
        ).setApplicationName("Email Counter").build();
    }
}

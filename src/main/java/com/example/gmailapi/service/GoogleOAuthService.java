package com.example.gmailapi.service;

import com.google.api.client.auth.oauth2.*;
import com.google.api.client.googleapis.auth.oauth2.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoogleOAuthService {

    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;

    @Value("${google.redirect.uri}")
    private String redirectUri;

    @Value("${google.scopes}")
    private String scopes;

    // token store
    private final Map<String, TokenResponse> tokenStore = new HashMap<>();

    private AuthorizationCodeFlow flow;

    public AuthorizationCodeFlow getFlow() throws Exception {
        if (flow == null) {
            GoogleClientSecrets details = new GoogleClientSecrets()
                    .setWeb(new GoogleClientSecrets.Details()
                            .setClientId(clientId)
                            .setClientSecret(clientSecret));

            flow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    GsonFactory.getDefaultInstance(),
                    details,
                    List.of(scopes)
            ).setAccessType("offline").build();
        }
        return flow;
    }

    public String getAuthUrl(String email) throws Exception {
        return getFlow().newAuthorizationUrl()
                .setRedirectUri(redirectUri)
                .setState(email)
                .build();
    }

    public String getRedirectUri() {
        return redirectUri;
    }
    
    public void saveToken(String email, TokenResponse token) {
        tokenStore.put(email, token);
    }

    public TokenResponse getToken(String email) {
    	System.out.println(tokenStore);
    	System.out.println(email);
        return tokenStore.get(email);
    }
}

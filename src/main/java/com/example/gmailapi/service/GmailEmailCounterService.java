package com.example.gmailapi.service;

import com.example.gmailapi.config.GmailProperties;
import com.example.gmailapi.exception.GmailIntegrationException;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.search.FromTerm;
import jakarta.mail.search.SearchTerm;

import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class GmailEmailCounterService {

    private final GmailProperties gmailProperties;

    public GmailEmailCounterService(GmailProperties gmailProperties) {
        this.gmailProperties = gmailProperties;
    }

    public int countEmailsFromSender(String senderEmail) {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        props.put("mail.imaps.host", gmailProperties.getImap().getHost());
        props.put("mail.imaps.port", String.valueOf(gmailProperties.getImap().getPort()));
        props.put("mail.imaps.ssl.enable", "true");

        Session session = Session.getInstance(props);

        Store store = null;
        Folder folder = null;

        try {
            store = session.getStore("imaps");
            store.connect(
                    gmailProperties.getImap().getHost(),
                    gmailProperties.getUsername(),
                    gmailProperties.getAppPassword()
            );

            folder = store.getFolder(gmailProperties.getImap().getFolder());
            folder.open(Folder.READ_ONLY);

            SearchTerm searchTerm = new FromTerm(new InternetAddress(senderEmail));
            Message[] messages = folder.search(searchTerm);

            return messages.length;

        } catch (Exception e) {
            throw new GmailIntegrationException("Failed to fetch emails from Gmail", e);
        } finally {
            try {
                if (folder != null && folder.isOpen()) {
                    folder.close(false);
                }
            } catch (MessagingException ignored) {
            }
            try {
                if (store != null && store.isConnected()) {
                    store.close();
                }
            } catch (MessagingException ignored) {
            }
        }
    }
}

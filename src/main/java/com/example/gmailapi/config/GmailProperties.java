package com.example.gmailapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "gmail")
public class GmailProperties {

    private String username;
    private String appPassword;
    private Imap imap = new Imap();

    public static class Imap {
        private String host;
        private int port;
        private String folder = "INBOX";

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getFolder() {
            return folder;
        }

        public void setFolder(String folder) {
            this.folder = folder;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAppPassword() {
        return appPassword;
    }

    public void setAppPassword(String appPassword) {
        this.appPassword = appPassword;
    }

    public Imap getImap() {
        return imap;
    }

    public void setImap(Imap imap) {
        this.imap = imap;
    }
}

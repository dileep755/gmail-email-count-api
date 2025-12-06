package com.example.gmailapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.example.gmailapi.config")
public class GmailEmailCountApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmailEmailCountApiApplication.class, args);
	}

}

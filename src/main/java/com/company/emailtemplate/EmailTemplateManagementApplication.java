package com.company.emailtemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailTemplateManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailTemplateManagementApplication.class, args);
        System.out.println(
                "Email Template Management System Started Successfully!"
        );
        
        System.out.println("MAIL_USERNAME configured: " +
                (System.getenv("MAIL_USERNAME") != null));

        System.out.println("MAIL_PASSWORD configured: " +
                (System.getenv("MAIL_PASSWORD") != null));

	}

}

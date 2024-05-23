package com.raman.emailsender.emailsenderapp;

import com.raman.emailsender.emailsenderapp.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootTest
public class EmailSenderTest {
    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest() {
        emailService.sendEmail("ramankumar4272@gmail.com", "Email from Spring", "Test Email from Spring Boot");
    }
    String htmlMail = """
            <h1 style='color:red'>Mail From Spring Boot</h1>
            <p style='border:1px'>This is a test email from Spring Boot</p>
            """;
    @Test
    void htmlMailSendTest() {
        emailService.sendEmailWithHtml("ramankumar4272@gmail.com", "Email from Spring", htmlMail);
    }

    String[] recipients = {"ramankumar4272@gmail.com", "kraman938@gmail.com"};
    @Test
    void sendMailtoMultipleRecipientsTest() {
        emailService.sendEmail(recipients, "Email from Spring", "Test Email from Spring Boot");
    }

    @Test
    void sendEmailWithAttachmentTest() {
        emailService.sendEmailWithAttachment(
                "ramankumar4272@gmail.com",
                "Email from Spring",
                "Attachments",
                new File("C:\\Users\\krama\\OneDrive\\Pictures\\Screenshots\\Screenshot 2024-03-24 201740.png")
        );
    }

    @Test
    void sendEmailWithAttachmentWithStreamTest() {
        File file = new File("C:\\Users\\krama\\OneDrive\\Pictures\\Screenshots\\Screenshot 2024-03-24 201740.png");
        try {
            InputStream inputStream = new FileInputStream(file);
            emailService.sendEmailWithAttachment(
                    "ramankumar4272@gmail.com",
                    "Email from Spring",
                    "Attachments", inputStream
                    );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    //receiving email test
    @Test
    void getInboxMessagesTest() {
        emailService.getInboxMessages();
    }
}

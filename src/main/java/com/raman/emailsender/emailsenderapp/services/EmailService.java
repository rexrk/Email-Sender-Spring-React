package com.raman.emailsender.emailsenderapp.services;

import com.raman.emailsender.emailsenderapp.entity.Messages;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface EmailService {
    //single email
    void sendEmail(String to, String subject, String body);

    //multiple email
    void sendEmail(String[] to, String subject, String body);

    //send email with html
    void sendEmailWithHtml(String to, String subject, String htmlBody);

    //send email with attachments
    void sendEmailWithAttachment(String to, String subject, String body, File file);

    //send email with attachments
    void sendEmailWithAttachment(String to, String subject, String body, InputStream is);


    //Receive emails
    List<Messages> getInboxMessages();
}

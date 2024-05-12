package com.raman.emailsender.emailsenderapp.services;

import java.io.File;
import java.io.InputStream;

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

}

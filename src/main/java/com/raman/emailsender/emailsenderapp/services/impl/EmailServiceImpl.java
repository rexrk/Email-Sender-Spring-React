package com.raman.emailsender.emailsenderapp.services.impl;

import com.raman.emailsender.emailsenderapp.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String EMAIL_FROM;

    private final JavaMailSender javaMailSender;

    private final Logger logger;
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        this.logger = LoggerFactory.getLogger(EmailService.class);
    }


    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom(EMAIL_FROM);
        javaMailSender.send(simpleMailMessage);
        logger.info("Email sent to {}", to);
    }

    @Override
    public void sendEmail(String[] to, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom(EMAIL_FROM);
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlBody) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(htmlBody, true);
            mimeMessageHelper.setFrom(EMAIL_FROM);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String body, File file) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(EMAIL_FROM);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.addAttachment(file.getName(), file);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void sendEmailWithAttachment(String to, String subject, String body, InputStream is) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(EMAIL_FROM);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);

            File file = new File("src/main/resources/Attachments/test.png");
            Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fsr = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(fsr.getFilename(), fsr);

            javaMailSender.send(mimeMessage);
            logger.info("Email sent successfully to {}", to);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }

    }
}

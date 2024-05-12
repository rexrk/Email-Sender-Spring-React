package com.raman.emailsender.emailsenderapp.controllers;

import com.raman.emailsender.emailsenderapp.services.CustomResponse;
import com.raman.emailsender.emailsenderapp.services.EmailRequest;
import com.raman.emailsender.emailsenderapp.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<CustomResponse> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmailWithHtml(request.getTo(), request.getSubject(), request.getBody());
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .response("Email sent successfully")
                        .httpStatus(HttpStatus.OK).success(true).build());
    }

    @PostMapping("/send-with-file")
    public ResponseEntity<CustomResponse> sendEmailWithAttachment(@RequestPart EmailRequest request, @RequestPart MultipartFile file ) throws IOException {
        emailService.sendEmailWithAttachment(request.getTo(), request.getSubject(), request.getBody(), file.getInputStream());
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .response("Email sent successfully")
                        .httpStatus(HttpStatus.OK).success(true).build());
    }

}

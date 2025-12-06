package com.pifirm.domain.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {

    private static final Logger log = LogManager.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Synchronous method
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = createMessage(to, subject, body);
            log.info("Sending email synchronously...");
            mailSender.send(message);
            log.info("Email sent synchronously");
        } catch (Exception ex) {
            log.error("Error sending email synchronously: {}", ex.getMessage(), ex);
        }
    }

    // Asynchronous method
    @Async
    public CompletableFuture<Void> sendEmailAsync(String to, String subject, String body) {
        try {
            SimpleMailMessage message = createMessage(to, subject, body);
            log.info("Sending email asynchronously...");
            mailSender.send(message);
            log.info("Email sent asynchronously");
            return CompletableFuture.completedFuture(null);
        } catch (Exception ex) {
            log.error("Error sending email asynchronously: {}", ex.getMessage(), ex);
            return CompletableFuture.failedFuture(ex);
        }
    }

    private SimpleMailMessage createMessage(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("webbank404@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        return message;
    }
}

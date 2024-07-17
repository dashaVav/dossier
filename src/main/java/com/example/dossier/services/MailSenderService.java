package com.example.dossier.services;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void send(String to, String subject, String text) throws MessagingException {
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, false);
//        helper.setSubject(subject);
//        helper.setFrom(from);
//        helper.setTo(to);
//        helper.setReplyTo(from);
//        helper.setText(text);
//
//        mailSender.send(message);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}

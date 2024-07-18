package com.example.dossier.service.impl;

import com.example.dossier.service.MailSenderService;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void send(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false);
        helper.setSubject(subject);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText(text);

        mailSender.send(message);
    }

    @Override
    public void send(String to, String subject, String text, String fileName, DataSource file) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText(text);
        helper.addAttachment(fileName, file);

        mailSender.send(message);
    }
}

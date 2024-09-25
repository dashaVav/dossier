package com.example.dossier.service;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;

public interface MailSenderService {
    void send(String to, String subject, String text) throws MessagingException;

    void send(String to, String subject, String text, String fileName, DataSource file) throws MessagingException;
}

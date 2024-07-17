package com.example.dossier.services;

import com.example.dossier.dtos.EmailMessage;
import com.example.dossier.model.MailContent;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DossierService {
    private final MailSenderService mailSender;
    private final MailContentGeneratorService mailContentGeneratorService;

    public void finishRegistration(EmailMessage emailMessage) throws MessagingException, IOException {
        MailContent mailContent = mailContentGeneratorService.readContentForFinishRegistration();
        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText());
    }

    public void createDocuments(EmailMessage emailMessage) throws MessagingException, IOException {
        MailContent mailContent = mailContentGeneratorService.readContentForCreateDocuments();
        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText());
    }

    public void sendDocuments(EmailMessage emailMessage) throws MessagingException {
        mailSender.send(emailMessage.getAddress(), "send-documents", "go!");
    }

    public void sendSes(EmailMessage emailMessage) throws MessagingException {
        mailSender.send(emailMessage.getAddress(), "sendSes", "go!");
    }

    public void creditIssued(EmailMessage emailMessage) throws MessagingException {
        mailSender.send(emailMessage.getAddress(), "creditIssued", "go!");
    }

    public void applicationDenied(EmailMessage emailMessage) throws MessagingException {
        mailSender.send(emailMessage.getAddress(), "applicationDenied", "go!");
    }
}

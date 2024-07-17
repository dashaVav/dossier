package com.example.dossier.services;

import com.example.dossier.dtos.ApplicationDTO;
import com.example.dossier.dtos.EmailMessage;
import com.example.dossier.model.MailContent;
import com.example.dossier.services.client.DealClient;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DossierService {
    private final MailSenderService mailSender;
    private final MailContentGeneratorService mailContentGeneratorService;
    private final DealClient dealClient;

    public void finishRegistration(EmailMessage emailMessage) throws MessagingException, IOException {
        MailContent mailContent = mailContentGeneratorService.readContentForFinishRegistration();
        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText());
    }

    public void createDocuments(EmailMessage emailMessage) throws MessagingException, IOException {
        MailContent mailContent = mailContentGeneratorService.readContentForCreateDocuments();
        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText());
    }

    public void sendDocuments(EmailMessage emailMessage) throws MessagingException {
        ApplicationDTO applicationDTO = dealClient.getApplication(emailMessage.getApplicationId());
        dealClient.updateApplicationStatus(emailMessage.getApplicationId());

        mailSender.send(emailMessage.getAddress(), "send-documents", applicationDTO.toString());
    }

    public void sendSes(EmailMessage emailMessage) throws MessagingException {
        String sesCode = dealClient.getSesCode(emailMessage.getApplicationId()).getCode();
        mailSender.send(emailMessage.getAddress(), "sendSes", sesCode);
    }

    public void creditIssued(EmailMessage emailMessage) throws MessagingException {
        mailSender.send(emailMessage.getAddress(), "creditIssued", "go!");
    }

    public void applicationDenied(EmailMessage emailMessage) throws MessagingException {
        mailSender.send(emailMessage.getAddress(), "applicationDenied", "done!");
    }
}

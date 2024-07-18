package com.example.dossier.service.impl;

import com.example.dossier.dto.ApplicationDTO;
import com.example.dossier.dto.EmailMessage;
import com.example.dossier.model.MailContent;
import com.example.dossier.service.DealClient;
import com.example.dossier.service.DossierService;
import com.example.dossier.service.MailContentGeneratorService;
import com.example.dossier.service.MailSenderService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DossierServiceImpl implements DossierService {
    private final MailSenderService mailSender;
    private final MailContentGeneratorService mailContentGeneratorService;
    private final DealClient dealClient;

    @Override
    public void finishRegistration(EmailMessage emailMessage) throws MessagingException, IOException {
        MailContent mailContent = mailContentGeneratorService
                .readContentForFinishRegistration(emailMessage.getApplicationId());
        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText());
    }

    @Override
    public void createDocuments(EmailMessage emailMessage) throws MessagingException, IOException {
        MailContent mailContent = mailContentGeneratorService.
                readContentForCreateDocuments(emailMessage.getApplicationId());
        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText());
    }

    @Override
    public void sendDocuments(EmailMessage emailMessage) throws MessagingException, IOException {
        ApplicationDTO applicationDTO = dealClient.getApplication(emailMessage.getApplicationId());
        dealClient.updateApplicationStatus(emailMessage.getApplicationId());

        MailContent mailContent = mailContentGeneratorService
                .readContentForSendDocuments(emailMessage.getApplicationId(), applicationDTO);
        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText(), "doc.txt", mailContent.getFile());
    }

    @Override
    public void sendSes(EmailMessage emailMessage) throws MessagingException, IOException {
        String sesCode = dealClient.getSesCode(emailMessage.getApplicationId()).getCode();
        MailContent mailContent = mailContentGeneratorService
                .readContentForSendSes(emailMessage.getApplicationId(), sesCode);

        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText());
    }

    @Override
    public void creditIssued(EmailMessage emailMessage) throws MessagingException, IOException {
        MailContent mailContent = mailContentGeneratorService
                .readContentForCreditIssued(emailMessage.getApplicationId());
        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText());
    }

    @Override
    public void applicationDenied(EmailMessage emailMessage) throws MessagingException, IOException {
        MailContent mailContent = mailContentGeneratorService
                .readContentForApplicationDenied(emailMessage.getApplicationId());
        mailSender.send(emailMessage.getAddress(), mailContent.getSubject(), mailContent.getText());
    }
}

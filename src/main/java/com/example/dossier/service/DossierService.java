package com.example.dossier.service;

import com.example.dossier.dto.EmailMessage;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface DossierService {
    void finishRegistration(EmailMessage emailMessage) throws MessagingException, IOException;

    void createDocuments(EmailMessage emailMessage) throws MessagingException, IOException;

    void sendDocuments(EmailMessage emailMessage) throws MessagingException, IOException;

    void sendSes(EmailMessage emailMessage) throws MessagingException, IOException;

    void creditIssued(EmailMessage emailMessage) throws MessagingException, IOException;

    void applicationDenied(EmailMessage emailMessage) throws MessagingException, IOException;
}

package com.example.dossier.service;

import com.example.dossier.dto.ApplicationDTO;
import com.example.dossier.model.MailContent;

import java.io.IOException;

public interface MailContentGeneratorService {
    MailContent readContentForFinishRegistration(Long applicationId) throws IOException;

    MailContent readContentForCreateDocuments(Long applicationId) throws IOException;

    MailContent readContentForSendDocuments(Long applicationId, ApplicationDTO application) throws IOException;

    MailContent readContentForSendSes(Long applicationId, String sesCode) throws IOException;

    MailContent readContentForCreditIssued(Long applicationId) throws IOException;

    MailContent readContentForApplicationDenied(Long applicationId) throws IOException;
}

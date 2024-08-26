package com.example.dossier.service.impl;

import com.example.dossier.dto.ApplicationDTO;
import com.example.dossier.model.MailContent;
import com.example.dossier.service.MailContentGeneratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class MailContentGeneratorServiceImpl implements MailContentGeneratorService {
    @Override
    public MailContent readContentForFinishRegistration(Long applicationId) throws IOException {
        return readContentForMail("content/finish-registration.json", applicationId);
    }

    @Override
    public MailContent readContentForCreateDocuments(Long applicationId) throws IOException {
        return readContentForMail("content/create-documents.json", applicationId);
    }

    @Override
    public MailContent readContentForSendDocuments(Long applicationId, ApplicationDTO application) throws IOException {
        return readContentForMail("content/send-documents.json", applicationId).setFile(createDataSource(application));
    }

    private DataSource createDataSource(ApplicationDTO application) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonString = objectMapper.writeValueAsString(application);
        return new ByteArrayDataSource(jsonString.getBytes(), "text/plain");
    }

    @Override
    public MailContent readContentForSendSes(Long applicationId, String sesCode) throws IOException {
        return readContentForMail("content/send-ses.json", applicationId, sesCode);
    }

    @Override
    public MailContent readContentForCreditIssued(Long applicationId) throws IOException {
        return readContentForMail("content/credit-issued.json", applicationId);
    }

    @Override
    public MailContent readContentForApplicationDenied(Long applicationId) throws IOException {
        return readContentForMail("content/application-denied.json", applicationId);
    }

    private MailContent readContentForMail(String path, Long applicationId) throws IOException {
        JSONObject dataForLoan = readFile(path);

        MailContent mailContent = new MailContent();
        return mailContent
                .setSubject(dataForLoan.getString("subject"))
                .setText(String.format(dataForLoan.getString("text"), applicationId));
    }

    private MailContent readContentForMail(String path, Long applicationId, String sesCode) throws IOException {
        JSONObject dataForLoan = readFile(path);

        return new MailContent()
                .setSubject(dataForLoan.getString("subject"))
                .setText(String.format(dataForLoan.getString("text"), applicationId, sesCode));
    }

    private JSONObject readFile(String path) throws IOException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        return new JSONObject(new String(Files.readAllBytes(file.toPath())));
    }
}

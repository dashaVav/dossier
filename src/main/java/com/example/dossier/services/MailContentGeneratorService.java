package com.example.dossier.services;

import com.example.dossier.model.MailContent;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class MailContentGeneratorService {
    public MailContent readContentForFinishRegistration() throws IOException {
        return readContentForMail("content/finish-registration.json");
    }

    public MailContent readContentForCreateDocuments() throws IOException {
        return readContentForMail("content/create-documents.json");
    }

    private MailContent readContentForMail(String path) throws IOException {
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + path);
        JSONObject dataForLoan = new JSONObject(new String(Files.readAllBytes(file.toPath())));

        MailContent mailContent = new MailContent();
        return mailContent.setSubject(dataForLoan.getString("subject")).setText(dataForLoan.getString("text"));
    }
}

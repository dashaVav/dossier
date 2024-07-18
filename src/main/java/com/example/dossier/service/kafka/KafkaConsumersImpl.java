package com.example.dossier.service.kafka;

import com.example.dossier.dto.EmailMessage;
import com.example.dossier.service.DlqProducer;
import com.example.dossier.service.DossierService;
import com.example.dossier.service.KafkaConsumers;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumersImpl implements KafkaConsumers {
    private final DossierService dossierService;
    private final DlqProducer dlqProducer;

    @Override
    public void listenFinishRegistration(@Payload EmailMessage emailMessage) {
        try {
            dossierService.finishRegistration(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }

    @Override
    public void listenCreateDocuments(@Payload EmailMessage emailMessage) {
        try {
            dossierService.createDocuments(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }

    @Override
    public void listenSendDocuments(@Payload EmailMessage emailMessage) {
        try {
            dossierService.sendDocuments(emailMessage);
        } catch (Exception e) {
            System.out.println(e);
            dlqProducer.send(emailMessage);
        }
    }

    @Override
    public void listenSendSes(@Payload EmailMessage emailMessage) {
        try {
            dossierService.sendSes(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }

    @Override
    public void listenCreditIssued(@Payload EmailMessage emailMessage) {
        try {
            dossierService.creditIssued(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }

    @Override
    public void listenApplicationDenied(@Payload EmailMessage emailMessage) {
        try {
            dossierService.applicationDenied(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }
}

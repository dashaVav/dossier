package com.example.dossier.services.kafka;

import com.example.dossier.dtos.EmailMessage;
import com.example.dossier.services.DossierService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumers {
    private final DossierService dossierService;
    private final DlqProducer dlqProducer;

    @KafkaListener(
            topics = "${spring.kafka.topic.finish-registration}",
            groupId = "${spring.kafka.group-id.finish-registration}",
            containerFactory = "factory"
    )
    public void listenFinishRegistration(@Payload EmailMessage emailMessage) {
        try {
            dossierService.finishRegistration(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.create-documents}",
            groupId = "${spring.kafka.group-id.create-documents}",
            containerFactory = "factory"
    )
    public void listenCreateDocuments(@Payload EmailMessage emailMessage) {
        try {
            dossierService.createDocuments(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.send-documents}",
            groupId = "${spring.kafka.group-id.send-documents}",
            containerFactory = "factory"
    )
    public void listenSendDocuments(@Payload EmailMessage emailMessage) {
        try {
            dossierService.sendDocuments(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.send-ses}",
            groupId = "${spring.kafka.group-id.send-ses}",
            containerFactory = "factory"
    )
    public void listenSendSes(@Payload EmailMessage emailMessage) {
        try {
            dossierService.sendSes(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.credit-issued}",
            groupId = "${spring.kafka.group-id.credit-issued}",
            containerFactory = "factory"
    )
    public void listenCreditIssued(@Payload EmailMessage emailMessage) {
        try {
            dossierService.creditIssued(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.application-denied}",
            groupId = "${spring.kafka.group-id.application-denied}",
            containerFactory = "factory"
    )
    public void listenApplicationDenied(@Payload EmailMessage emailMessage) {
        try {
            dossierService.applicationDenied(emailMessage);
        } catch (Exception e) {
            dlqProducer.send(emailMessage);
        }
    }
}

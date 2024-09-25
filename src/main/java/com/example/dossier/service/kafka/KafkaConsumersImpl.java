package com.example.dossier.service.kafka;

import com.example.dossier.dto.EmailMessage;
import com.example.dossier.service.DlqProducer;
import com.example.dossier.service.DossierService;
import com.example.dossier.service.KafkaConsumers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumersImpl implements KafkaConsumers {
    private final DossierService dossierService;
    private final DlqProducer dlqProducer;

    private void printLogInfo(Long applicationId, String topic) {
        log.info("Message with application id - {} consumed from topic - {}",
                applicationId, topic);
    }

    private void printLogError(Long applicationId, String topic, String errorMessage) {
        log.error("Exception while processing message with application id {} from topic {}: {}",
                applicationId, topic, errorMessage);
    }

    @Value("${spring.kafka.topic.finish-registration}")
    private String finishRegistrationTopic;

    @Override
    public void listenFinishRegistration(@Payload EmailMessage emailMessage) {
        printLogInfo(emailMessage.getApplicationId(), finishRegistrationTopic);
        try {
            dossierService.finishRegistration(emailMessage);
        } catch (Exception e) {
            printLogError(emailMessage.getApplicationId(), finishRegistrationTopic, e.getMessage());
            dlqProducer.send(emailMessage);
        }
    }

    @Value("${spring.kafka.topic.create-documents}")
    private String createDocumentsTopic;

    @Override
    public void listenCreateDocuments(@Payload EmailMessage emailMessage) {
        printLogInfo(emailMessage.getApplicationId(), createDocumentsTopic);
        try {
            dossierService.createDocuments(emailMessage);
        } catch (Exception e) {
            printLogError(emailMessage.getApplicationId(), createDocumentsTopic, e.getMessage());
            dlqProducer.send(emailMessage);
        }
    }

    @Value("${spring.kafka.topic.send-documents}")
    private String sendDocumentsTopic;

    @Override
    public void listenSendDocuments(@Payload EmailMessage emailMessage) {
        printLogInfo(emailMessage.getApplicationId(), sendDocumentsTopic);
        try {
            dossierService.sendDocuments(emailMessage);
        } catch (Exception e) {
            printLogError(emailMessage.getApplicationId(), sendDocumentsTopic, e.getMessage());
            dlqProducer.send(emailMessage);
        }
    }

    @Value("${spring.kafka.topic.send-ses}")
    private String sendSesTopic;

    @Override
    public void listenSendSes(@Payload EmailMessage emailMessage) {
        printLogInfo(emailMessage.getApplicationId(), sendSesTopic);
        try {
            dossierService.sendSes(emailMessage);
        } catch (Exception e) {
            printLogError(emailMessage.getApplicationId(), sendSesTopic, e.getMessage());
            dlqProducer.send(emailMessage);
        }
    }

    @Value("${spring.kafka.topic.credit-issued}")
    private String creditIssuedTopic;

    @Override
    public void listenCreditIssued(@Payload EmailMessage emailMessage) {
        printLogInfo(emailMessage.getApplicationId(), creditIssuedTopic);
        try {
            dossierService.creditIssued(emailMessage);
        } catch (Exception e) {
            printLogError(emailMessage.getApplicationId(), creditIssuedTopic, e.getMessage());
            dlqProducer.send(emailMessage);
        }
    }

    @Value("${spring.kafka.topic.application-denied}")
    private String applicationDeniedTopic;

    @Override
    public void listenApplicationDenied(@Payload EmailMessage emailMessage) {
        printLogInfo(emailMessage.getApplicationId(), applicationDeniedTopic);
        try {
            dossierService.applicationDenied(emailMessage);
        } catch (Exception e) {
            printLogError(emailMessage.getApplicationId(), applicationDeniedTopic, e.getMessage());
            dlqProducer.send(emailMessage);
        }
    }
}

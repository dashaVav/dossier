package com.example.dossier.service;

import com.example.dossier.dto.EmailMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

public interface KafkaConsumers {
    @KafkaListener(
            topics = "${spring.kafka.topic.finish-registration}",
            groupId = "${spring.kafka.group-id.finish-registration}",
            containerFactory = "factory"
    )
    void listenFinishRegistration(@Payload EmailMessage emailMessage);


    @KafkaListener(
            topics = "${spring.kafka.topic.create-documents}",
            groupId = "${spring.kafka.group-id.create-documents}",
            containerFactory = "factory"
    )
    void listenCreateDocuments(@Payload EmailMessage emailMessage);


    @KafkaListener(
            topics = "${spring.kafka.topic.send-documents}",
            groupId = "${spring.kafka.group-id.send-documents}",
            containerFactory = "factory"
    )
    void listenSendDocuments(@Payload EmailMessage emailMessage);


    @KafkaListener(
            topics = "${spring.kafka.topic.send-ses}",
            groupId = "${spring.kafka.group-id.send-ses}",
            containerFactory = "factory"
    )
    void listenSendSes(@Payload EmailMessage emailMessage);


    @KafkaListener(
            topics = "${spring.kafka.topic.credit-issued}",
            groupId = "${spring.kafka.group-id.credit-issued}",
            containerFactory = "factory"
    )
    void listenCreditIssued(@Payload EmailMessage emailMessage);

    @KafkaListener(
            topics = "${spring.kafka.topic.application-denied}",
            groupId = "${spring.kafka.group-id.application-denied}",
            containerFactory = "factory"
    )
    void listenApplicationDenied(@Payload EmailMessage emailMessage);
}

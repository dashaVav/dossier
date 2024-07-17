package com.example.dossier.services.kafka;

import com.example.dossier.dtos.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DlqProducer {
    private final KafkaTemplate<String, EmailMessage> kafkaProducer;

    @Value("${spring.kafka.topic.dlq-topic}")
    private String topic;

    public void send(EmailMessage errorMessage) {
        kafkaProducer.send(topic, errorMessage);
    }
}

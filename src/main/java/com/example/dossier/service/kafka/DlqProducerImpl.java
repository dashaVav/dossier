package com.example.dossier.service.kafka;

import com.example.dossier.dto.EmailMessage;
import com.example.dossier.service.DlqProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DlqProducerImpl implements DlqProducer {
    private final KafkaTemplate<String, EmailMessage> kafkaProducer;

    @Value("${spring.kafka.topic.dlq-topic}")
    private String topic;

    @Override
    public void send(EmailMessage errorMessage) {
        kafkaProducer.send(topic, errorMessage);
    }
}

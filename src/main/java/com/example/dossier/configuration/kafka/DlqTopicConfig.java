package com.example.dossier.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DlqTopicConfig {
    @Value("${spring.kafka.topic.dlq-topic}")
    private String dqlTopic;

    @Bean
    public NewTopic newDlqTopic() {
        return new NewTopic(dqlTopic, 1, (short) 1);
    }
}

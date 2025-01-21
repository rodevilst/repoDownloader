package com.forgeflow.repodownloader.messaging.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgeflow.repodownloader.models.RepositoryEventModel;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RepositoryEventPublisher {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public RepositoryEventPublisher(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publishRepositoryCreatedEvent(RepositoryEventModel event) {
        try {
            String eventJson = objectMapper.writeValueAsString(event);
            kafkaTemplate.send("repo-events", eventJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

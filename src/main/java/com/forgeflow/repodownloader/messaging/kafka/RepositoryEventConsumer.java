//package com.forgeflow.repodownloader.messaging.kafka;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.forgeflow.repodownloader.models.RepositoryEventModel;
//import com.forgeflow.repodownloader.models.RepositoryStatus;
//import org.springframework.context.event.EventListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RepositoryEventConsumer {
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final ObjectMapper objectMapper;
//
//    public RepositoryEventConsumer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
//        this.kafkaTemplate = kafkaTemplate;
//        this.objectMapper = objectMapper;
//    }
//
//    @EventListener
//    public void handleRepositoryEvent(RepositoryEventModel event) {
//        try {
//            String message = objectMapper.writeValueAsString(event.getRepositoryId());
//            System.out.println(message);
//            kafkaTemplate.send("repo-events", message);
//        } catch (Exception e) {
//            System.err.println("Failed to serialize transaction: " + e.getMessage());
//        }
//    }
//}
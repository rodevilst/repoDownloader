package com.forgeflow.repodownloader.messaging.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgeflow.repodownloader.models.RepositoryEventModel;
import com.forgeflow.repodownloader.models.RepositoryStatus;
import com.forgeflow.repodownloader.services.ManageService;
import com.forgeflow.repodownloader.services.directory.DirectoryService;
import com.forgeflow.repodownloader.services.log.LogService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RepositoryListener {
    private final ObjectMapper objectMapper;

    private final ManageService manageService;

    public RepositoryListener(ObjectMapper objectMapper, ManageService manageService) {
        this.objectMapper = objectMapper;
        this.manageService = manageService;
    }

    @KafkaListener(topics = "repo-events", groupId = "${spring.application.name}-consumer-group")
    public void listen(String event) {
        try {
            RepositoryEventModel repositoryEventModel = objectMapper.readValue(event, RepositoryEventModel.class);

            RepositoryStatus status = repositoryEventModel.getStatus();
            switch (status) {
                case RECEIVED -> manageService.toDownloading(repositoryEventModel);
                case DOWNLOADING -> manageService.toDownload(repositoryEventModel);
            }
        } catch (Exception e) {
            System.err.println("Failed to process message: " + e.getMessage());
        }
    }
}

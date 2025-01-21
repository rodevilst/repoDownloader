package com.forgeflow.repodownloader.services.log;

import com.forgeflow.repodownloader.dto.KafkaMessage;
import com.forgeflow.repodownloader.messaging.kafka.RepositoryEventPublisher;
import com.forgeflow.repodownloader.models.RepositoryEventModel;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private final RepositoryEventPublisher repositoryEventPublisher;

    public LogService(RepositoryEventPublisher repositoryEventPublisher) {
        this.repositoryEventPublisher = repositoryEventPublisher;
    }

    public void log(RepositoryEventModel repositoryEventModel) {
        repositoryEventPublisher.publishRepositoryCreatedEvent(repositoryEventModel);
    }
}

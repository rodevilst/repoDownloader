package com.forgeflow.repodownloader.messaging.kafka;


import com.forgeflow.repodownloader.dto.KafkaMessage;
import com.forgeflow.repodownloader.models.RepositoryEventModel;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RepositoryEvent extends ApplicationEvent {
    private final RepositoryEventModel repositoryEventModel;

    public RepositoryEvent(Object source, RepositoryEventModel repositoryEventModel) {
        super(source);
        this.repositoryEventModel = repositoryEventModel;
    }

}
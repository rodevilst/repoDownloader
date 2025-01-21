package com.forgeflow.repodownloader.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepositoryEventModel {
    private String repositoryId;
    private String repositoryUrl;
    private RepositoryStatus status;
    private LocalDateTime timestamp;
    private String owner;
    private String repositoryName;
}

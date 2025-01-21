package com.forgeflow.repodownloader.models;

public enum RepositoryStatus {
    RECEIVED,
    DOWNLOADING,
    DOWNLOADED,
    PUSHING_TO_NEXUS,
    PUSHED_TO_NEXUS,
    DEPLOYING,
    DEPLOYED,
    FAILED
}

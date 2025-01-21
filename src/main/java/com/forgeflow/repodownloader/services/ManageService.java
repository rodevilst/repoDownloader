package com.forgeflow.repodownloader.services;

import com.forgeflow.repodownloader.models.RepositoryEventModel;
import com.forgeflow.repodownloader.models.RepositoryStatus;
import com.forgeflow.repodownloader.services.directory.DirectoryService;
import com.forgeflow.repodownloader.services.git.GitCloneService;
import com.forgeflow.repodownloader.services.log.LogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ManageService {
    private final DirectoryService directoryService;
    private final LogService logService;
    private final GitCloneService gitCloneService;


    public ManageService(DirectoryService directoryService, LogService logService, GitCloneService gitCloneService) {
        this.directoryService = directoryService;
        this.logService = logService;
        this.gitCloneService = gitCloneService;
    }

    public void toDownloading(RepositoryEventModel repository) throws InterruptedException {
        String owner = repository.getOwner();
        String repositoryName = repository.getRepositoryName();
        directoryService.createDirectoryForUserProject(owner, repositoryName);
        repository.setTimestamp(LocalDateTime.now());
        repository.setStatus(RepositoryStatus.DOWNLOADING);
        logService.log(repository);
    }

    public void toDownload(RepositoryEventModel repository) throws InterruptedException {
        String localDir = "C:\\Users\\rosol\\OneDrive\\Документы\\GitHub\\ForgeFlow\\repositories";
        String projectDir = localDir + "/" + repository.getOwner() + "/" + repository.getRepositoryName();
        gitCloneService.cloneGithubRepository(repository.getRepositoryUrl(),projectDir);
        repository.setTimestamp(LocalDateTime.now());
        repository.setStatus(RepositoryStatus.DOWNLOADED);
        logService.log(repository);
    }
}

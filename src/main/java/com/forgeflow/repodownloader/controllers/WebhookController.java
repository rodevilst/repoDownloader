package com.forgeflow.repodownloader.controllers;

import com.forgeflow.repodownloader.dto.git.GitHubWebhookPayload;
import com.forgeflow.repodownloader.exception.FileNotFoundException;
import com.forgeflow.repodownloader.models.RepositoryEventModel;
import com.forgeflow.repodownloader.models.RepositoryStatus;
import com.forgeflow.repodownloader.services.directory.DirectoryService;
import com.forgeflow.repodownloader.services.git.GitCloneService;
import com.forgeflow.repodownloader.services.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/hook")
public class WebhookController {
    final GitCloneService gitCloneService;
    final DirectoryService directoryService;
    final LogService logService;
    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);

    public WebhookController(GitCloneService gitCloneService, DirectoryService directoryService, LogService logService) {
        this.gitCloneService = gitCloneService;
        this.directoryService = directoryService;
        this.logService = logService;
    }

    @PostMapping
    public ResponseEntity<String> handleGitHubWebhook(@RequestBody GitHubWebhookPayload payload,
                                                      @RequestHeader("X-GitHub-Event") String eventType,
                                                      @RequestHeader("X-GitHub-Hook-ID") String hookId
    ) {
        String id = payload.getRepository().getId();
        String clone_url = payload.getRepository().getClone_url();
        LocalDateTime now = LocalDateTime.now();

        String repositoryName = payload.getRepository().getName();
        String ownerName = payload.getRepository().getOwner().getName();

        RepositoryEventModel repositoryEventModel = new RepositoryEventModel(id,clone_url, RepositoryStatus.RECEIVED,now,ownerName,repositoryName);
        logService.log(repositoryEventModel);

        try {
            return ResponseEntity.status(HttpStatus.OK).body("Webhook received");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return ResponseEntity.status(HttpStatus.OK).body("Webhook received");
        }

    }
}

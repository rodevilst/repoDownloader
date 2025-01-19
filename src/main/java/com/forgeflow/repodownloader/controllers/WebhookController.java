package com.forgeflow.repodownloader.controllers;

import com.forgeflow.repodownloader.dto.git.GitHubWebhookPayload;
import com.forgeflow.repodownloader.exception.FileNotFoundException;
import com.forgeflow.repodownloader.services.git.GitCloneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/hook")
public class WebhookController {
    final GitCloneService gitCloneService;
    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);
    private String localDir = "C:\\Users\\rosol\\OneDrive\\Документы\\GitHub\\ForgeFlow\\repositories";

    public WebhookController(GitCloneService gitCloneService) {
        this.gitCloneService = gitCloneService;
    }

    @PostMapping
    public ResponseEntity<String> handleGitHubWebhook(@RequestBody GitHubWebhookPayload payload,
                                                      @RequestHeader("X-GitHub-Event") String eventType,
                                                      @RequestHeader("X-GitHub-Hook-ID") String hookId
    ) {
        String clone_url = payload.getRepository().getClone_url();
        String projectFullName = payload.getRepository().getFull_name();
        String fullProjectDir = localDir + "\\" + projectFullName;
        gitCloneService.cloneGithubRepository(clone_url, fullProjectDir);
        try {
            String versionFromVersionJson = gitCloneService.getVersionFromVersionJson(fullProjectDir);
            System.out.println(versionFromVersionJson);
            return ResponseEntity.status(HttpStatus.OK).body("Webhook received");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            return ResponseEntity.status(HttpStatus.OK).body("Webhook received");
        }

    }
}

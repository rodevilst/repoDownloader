package com.forgeflow.repodownloader.controllers;

import com.forgeflow.repodownloader.dto.git.GitHubWebhookPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/hook")
public class WebhookController {
    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);
    @PostMapping
    public ResponseEntity<String> handleGitHubWebhook(@RequestBody GitHubWebhookPayload payload,
                                                @RequestHeader("X-GitHub-Event") String eventType,
                                                @RequestHeader("X-GitHub-Hook-ID") String hookId
    ) {
        System.out.println("Received webhook event: " + payload.getRef());
        System.out.println("Repository: " + payload.getRepository().getFullName());
        System.out.println("Pusher: " + payload.getPusher().getName());

        return ResponseEntity.status(HttpStatus.OK).body("Webhook received");
    }
}

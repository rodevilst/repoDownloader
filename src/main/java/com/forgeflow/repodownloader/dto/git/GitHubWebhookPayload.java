package com.forgeflow.repodownloader.dto.git;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GitHubWebhookPayload {
    private String ref;
    private String before;
    private String after;
    private Repository repository;
    private Pusher pusher;
    private Sender sender;
    private List<Commit> commits;
    private String ssh_url;
}

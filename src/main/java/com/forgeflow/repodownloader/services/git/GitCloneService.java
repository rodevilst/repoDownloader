package com.forgeflow.repodownloader.services.git;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgeflow.repodownloader.controllers.WebhookController;
import com.forgeflow.repodownloader.dto.git.VersionInfo;
import com.forgeflow.repodownloader.exception.FileNotFoundException;
import com.forgeflow.repodownloader.exception.ParseException;
import org.eclipse.jgit.api.Git;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class GitCloneService {
    private static final Logger logger = LoggerFactory.getLogger(GitCloneService.class);

    public void cloneGithubRepository(String sshUrl, String localDir) {
        downloadProject(sshUrl,localDir);
//        String projectVersion = getVersionFromVersionJson(localDir);
//        logger.info("project version: " + projectVersion);
    }

    public String getVersionFromVersionJson(String projectDir) {
        logger.info("get project version");
        ObjectMapper objectMapper = new ObjectMapper();
        File versionFile = new File(projectDir + "/versions.json");
        if (versionFile.exists()) {
            VersionInfo versionInfo = null;
            try {
                versionInfo = objectMapper.readValue(versionFile, VersionInfo.class);
            } catch (IOException e) {
                logger.error("cannot parse file");

                throw new ParseException("cannot parse file");
            }
            return versionInfo.getApp_version();
        } else {
            logger.error("file not found");
            throw new FileNotFoundException("file not found");
        }
    }


    private void downloadProject(String sshUrl, String localDir) {
        logger.info("start download project");
        try {
            Git.cloneRepository()
                    .setURI(sshUrl)
                    .setDirectory(new File(localDir))
                    .call();
            logger.info("project downloaded");
        } catch (Exception e) {
            logger.error("project download error");
            e.printStackTrace();
        }
    }
}

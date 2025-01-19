package com.forgeflow.repodownloader.services.git;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forgeflow.repodownloader.dto.git.VersionInfo;
import com.forgeflow.repodownloader.exception.FileNotFoundException;
import com.forgeflow.repodownloader.exception.ParseException;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class GitCloneService {
    public void cloneGithubRepository(String sshUrl, String localDir) {
        try {
            Git.cloneRepository()
                    .setURI(sshUrl)
                    .setDirectory(new File(localDir))
                    .call();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getVersionFromVersionJson(String projectDir) {
        ObjectMapper objectMapper = new ObjectMapper();
        File versionFile = new File(projectDir + "/versions.json");
        if (versionFile.exists()) {
            VersionInfo versionInfo = null;
            try {
                versionInfo = objectMapper.readValue(versionFile, VersionInfo.class);
            } catch (IOException e) {
                throw new ParseException("cannot parse file");
            }
            return versionInfo.getApp_version();
        } else {
            throw new FileNotFoundException("file not found");
        }
    }
}

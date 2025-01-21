package com.forgeflow.repodownloader.services.directory;


import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DirectoryService {
    private String localDir = "C:\\Users\\rosol\\OneDrive\\Документы\\GitHub\\ForgeFlow\\repositories";

    public void renameRepo(String directoryPath,String directoryNewName){
        File file = new File(directoryPath);
        file.mkdir();
        File projectWithoutVersion = new File(directoryPath);
        File projectWithVersion = new File(directoryNewName);
        try {
            FileUtils.moveDirectory(projectWithoutVersion, projectWithVersion);
            FileUtils.delete(new File(directoryPath));
        } catch (IOException e) {
        }
        file.renameTo(new File(directoryNewName));
    }

    public void createDirectoryForUserProject(String ownerName,String repositoryName) {
        String projectPath = localDir + "\\" + ownerName + "\\" + repositoryName;
        File file = new File(projectPath);
        if (file.mkdirs()) {
            System.out.println("Directory created: " + projectPath);
        } else {
            System.out.println("Failed to create directory or it already exists: " + projectPath);
        }
    }
}

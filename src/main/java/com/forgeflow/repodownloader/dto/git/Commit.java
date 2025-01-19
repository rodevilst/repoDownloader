package com.forgeflow.repodownloader.dto.git;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Commit {
    private String id;
    private String message;
}

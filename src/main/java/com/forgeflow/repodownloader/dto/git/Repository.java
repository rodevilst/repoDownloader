package com.forgeflow.repodownloader.dto.git;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Repository {
    private String id;
    private String name;
    private String full_name;
    private Owner owner;
}

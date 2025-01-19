package com.forgeflow.repodownloader.dto.git;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pusher {
    private String name;
    private String email;
}

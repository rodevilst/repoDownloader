package com.forgeflow.repodownloader.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class KafkaMessage implements Serializable {
    @JsonProperty("projectPath")
    private String projectSourceUrl;
    @JsonProperty("eventType")
    private String eventType;
}

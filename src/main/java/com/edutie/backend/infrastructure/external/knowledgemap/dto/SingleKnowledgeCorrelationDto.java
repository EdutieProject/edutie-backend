package com.edutie.backend.infrastructure.external.knowledgemap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

@Getter
public class SingleKnowledgeCorrelationDto {
    @JsonProperty
    private UUID correlatedSubjectId;
    @JsonProperty
    private UUID sourceSubjectId;
    @JsonProperty
    private Integer correlationFactor;
}

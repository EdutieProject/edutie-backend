package com.edutie.infrastructure.llm.learningresult.implementation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AssessmentDto {
    @JsonProperty
    private String feedbackString;
    @JsonProperty
    private int masteryPoints;
    @JsonProperty
    private UUID elementalRequirementId;
}

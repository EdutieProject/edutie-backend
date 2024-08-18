package com.edutie.backend.infrastucture.llm.dto;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProblemDetailDto {
    @JsonProperty
    LearningRequirementId learningRequirementId;
    @JsonProperty
    int qualifiedSubRequirements;
}

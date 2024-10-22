package com.edutie.backend.infrastucture.external.llm.dto.learningresult;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssessmentDto {
    @JsonProperty
    LearningRequirementId learningRequirementId;
    @JsonProperty
    int gradeNumber;
    @JsonProperty
    String feedbackText;
}

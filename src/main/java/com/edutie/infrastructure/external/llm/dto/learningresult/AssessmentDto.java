package com.edutie.infrastructure.external.llm.dto.learningresult;

import com.edutie.domain.core.education.learningsubject.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssessmentDto {
    @JsonProperty
    LearningRequirementId learningRequirementId;
    @JsonProperty
    int gradeNumber;
    @JsonProperty
    String feedbackText;
}

package com.edutie.infrastructure.external.llm.dto.learningresult;

import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssessmentDto {
    @JsonProperty
    LearningSubjectId learningSubjectId;
    @JsonProperty
    int gradeNumber;
    @JsonProperty
    String feedbackText;
}

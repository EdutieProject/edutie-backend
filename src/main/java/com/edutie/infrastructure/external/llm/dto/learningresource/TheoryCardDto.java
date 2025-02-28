package com.edutie.infrastructure.external.llm.dto.learningresource;

import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TheoryCardDto {
    @JsonProperty
    LearningSubjectId learningSubjectId;
    @JsonProperty
    String text;
}

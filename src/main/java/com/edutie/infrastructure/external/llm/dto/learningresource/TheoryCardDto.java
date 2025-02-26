package com.edutie.infrastructure.external.llm.dto.learningresource;

import com.edutie.domain.core.education.learningsubject.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TheoryCardDto {
    @JsonProperty
    LearningRequirementId learningRequirementId;
    @JsonProperty
    String text;
}

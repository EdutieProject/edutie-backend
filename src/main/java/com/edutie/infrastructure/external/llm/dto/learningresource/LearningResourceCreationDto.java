package com.edutie.infrastructure.external.llm.dto.learningresource;

import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.entities.activity.common.ActivityBase;
import com.edutie.domain.core.learning.learningexperience.valueobjects.Visualisation;
import com.edutie.domain.service.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.infrastructure.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO used for communication with LLM service. This DTO is the published language entity used between
 * LLM service and Edutie Backend for Learning Resource creation
 */
public class LearningResourceCreationDto implements ExternalInfrastructureDto<LearningExperience, LearningResourceGenerationSchema> {
    @JsonProperty
    private String activityText;
    @JsonProperty
    private Set<TheoryCardDto> theoryCards;
    @JsonProperty
    private String mermaidGraphString;
    @JsonProperty
    private Set<String> hints;

    @Override
    public LearningExperience intoDomainEntity(LearningResourceGenerationSchema schema) {
        Set<Hint> hints = this.hints.stream().map(Hint::create).collect(Collectors.toSet());
        Set<TheoryCard> theoryCards = this.theoryCards.stream().map(o -> TheoryCard.create(o.learningRequirementId, o.text)).collect(Collectors.toSet());
        return LearningExperience.create(
                schema.getStudentMetadata(),
                schema.getLearningResourceDefinition(),
                schema.getQualifiedRequirements(),
                ActivityBase.create(activityText, hints),
                theoryCards,
                new Visualisation(mermaidGraphString)
        );
    }
}

package com.edutie.backend.infrastructure.external.llm.dto.learningresource;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Activity;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.TheoryCard;
import com.edutie.backend.domain.personalization.learningresource.valueobjects.Visualisation;
import com.edutie.backend.domainservice.personalization.learningresource.schema.LearningResourceGenerationSchema;
import com.edutie.backend.infrastructure.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO used for communication with LLM service. This DTO is the published language entity used between
 * LLM service and Edutie Backend for Learning Resource creation
 */
public class LearningResourceCreationDto implements ExternalInfrastructureDto<LearningResource, LearningResourceGenerationSchema> {
    @JsonProperty
    private String activityText;
    @JsonProperty
    private Set<TheoryCardDto> theoryCards;
    @JsonProperty
    private String mermaidGraphString;
    @JsonProperty
    private Set<String> hints;

    @Override
    public LearningResource intoDomainEntity(LearningResourceGenerationSchema schema) {
        Set<Hint> hints = this.hints.stream().map(Hint::create).collect(Collectors.toSet());
        Set<TheoryCard> theoryCards = this.theoryCards.stream().map(o -> TheoryCard.create(o.learningRequirementId, o.text)).collect(Collectors.toSet());
        return LearningResource.create(
                schema.getStudentMetadata(),
                schema.getLearningResourceDefinition(),
                schema.getQualifiedRequirements(),
                Activity.create(activityText, hints),
                theoryCards,
                new Visualisation(mermaidGraphString)
        );
    }
}

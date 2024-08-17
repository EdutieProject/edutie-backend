package com.edutie.backend.infrastucture.llm.dto;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresource.entities.ProblemDetail;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO used for communication with LLM service. This DTO is the published language entity used between
 * LLM service and Edutie Backend for Learning Resource creation
 */
public class LearningResourceCreationDto {
    @JsonProperty
    private String activityText;
    @JsonProperty
    private String theoryOverviewText;
    @JsonProperty
    private String theorySummaryText;
    @JsonProperty
    private Set<String> hints;
    @JsonProperty
    private Set<ProblemDetailDto> problemDetails;

    @AllArgsConstructor
    public static class ProblemDetailDto {
        LearningRequirementId learningRequirementId;
        int qualifiedSubRequirements;
    }


    public LearningResource intoLearningResource(LearningResourceGenerationSchema generationSchema) {
        Set<Hint> hints = this.hints.stream().map(Hint::create).collect(Collectors.toSet());
        return LearningResource.create(
                generationSchema,
                activityText,
                hints,
                theoryOverviewText,
                theorySummaryText,
                problemDetails.stream().map(o-> ProblemDetail.create(o.learningRequirementId, o.qualifiedSubRequirements)).collect(Collectors.toSet())
        );
    }
}

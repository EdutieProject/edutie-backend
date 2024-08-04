package com.edutie.backend.infrastucture.llm.dto;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
import java.util.stream.Collectors;

public class LearningResourceDetailsDto {
    @JsonProperty
    private String activityText;
    @JsonProperty
    private String theoryOverviewText;
    @JsonProperty
    private String theorySummaryText;
    @JsonProperty
    private Set<String> hints;


    public LearningResource intoLearningResource(LearningResourceGenerationSchema generationSchema) {
        Set<Hint> hints = this.hints.stream().map(Hint::create).collect(Collectors.toSet());
        return LearningResource.create(generationSchema, activityText, hints, theoryOverviewText, theorySummaryText);
    }
}

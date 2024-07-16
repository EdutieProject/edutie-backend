package com.edutie.backend.infrastucture.llm.dto;

import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.entities.Hint;
import com.edutie.backend.domain.personalization.learningresourcegenerationschema.LearningResourceGenerationSchema;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

public class LearningResourceDetailsDto {
    private String activityText;
    private String theoryOverviewText;
    private String theorySummaryText;
    private Set<HintDto> hints;


    @Getter
    public static class HintDto {
        private String hintText;
    }

    public LearningResource intoLearningResource(LearningResourceGenerationSchema generationSchema) {
        Set<Hint> hints = this.hints.stream().map(o -> Hint.create(o.getHintText())).collect(Collectors.toSet());
        return LearningResource.create(generationSchema, activityText, hints, theoryOverviewText, theorySummaryText);
    }
}

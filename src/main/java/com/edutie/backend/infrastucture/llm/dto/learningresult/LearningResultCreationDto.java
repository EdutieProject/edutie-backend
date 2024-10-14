package com.edutie.backend.infrastucture.llm.dto.learningresult;

import com.edutie.backend.domain.personalization.assessmentschema.AssessmentSchema;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO used for communication with LLM service. This DTO is the published language entity used between
 * LLM service and Edutie Backend for Learning Result creation purpose.
 */
public class LearningResultCreationDto {
    @JsonProperty
    private final Set<AssessmentDto> assessments = new HashSet<>();
    @JsonProperty
    private String feedbackText;
    @JsonProperty
    private String feedbackLevel;

    public LearningResult intoLearningResult(AssessmentSchema assessmentSchema) {
        return LearningResult.create(
                assessmentSchema.getStudent(),
                assessmentSchema.getSolutionSubmission(),
                new Feedback(feedbackText, FeedbackType.fromString(feedbackLevel)),
                assessments.stream().map(o -> Assessment.create(
                        o.learningRequirementId,
                        new Grade(o.gradeNumber),
                        o.feedbackText,
                        assessmentSchema.getQualifiedRequirements().stream().toList()
                )).collect(Collectors.toSet()));
    }
}

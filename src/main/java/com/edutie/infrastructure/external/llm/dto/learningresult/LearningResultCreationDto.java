package com.edutie.infrastructure.external.llm.dto.learningresult;

import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.Assessment;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.service.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.infrastructure.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO used for communication with LLM service. This DTO is the published language entity used between
 * LLM service and Edutie Backend for Learning Result creation purpose.
 */
public class LearningResultCreationDto implements ExternalInfrastructureDto<LearningResult, AssessmentSchema> {
    @JsonProperty
    private final Set<AssessmentDto> assessments = new HashSet<>();
    @JsonProperty
    private String feedbackText;

    @Override
    public LearningResult intoDomainEntity(AssessmentSchema assessmentSchema) {
        return LearningResult.create(
                assessmentSchema.getSolutionSubmission(),
                new Feedback(feedbackText),
                assessments.stream().map(o -> Assessment.create(
                        o.learningRequirementId,
                        new Grade(o.gradeNumber),
                        Feedback.of(o.feedbackText),
                        assessmentSchema.getQualifiedRequirements().stream()
                                .filter(x -> x.getLearningRequirement().getId().equals(o.learningRequirementId)).toList()
                )).collect(Collectors.toSet()));
    }
}

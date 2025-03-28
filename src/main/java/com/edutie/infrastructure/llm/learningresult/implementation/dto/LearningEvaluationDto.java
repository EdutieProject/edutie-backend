package com.edutie.infrastructure.llm.learningresult.implementation.dto;

import com.edutie.domain.core.education.elementalrequirement.identitites.ElementalRequirementId;
import com.edutie.domain.core.learning.learningresult.entities.Assessment;
import com.edutie.domain.core.learning.learningresult.entities.LearningEvaluation;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.infrastructure.common.ExternalServiceDto;
import com.edutie.infrastructure.llm.learningresult.schema.LearningEvaluationGenerationSchema;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Set;
import java.util.stream.Collectors;

public class LearningEvaluationDto implements ExternalServiceDto<LearningEvaluation, LearningEvaluationGenerationSchema<?>> {
    private Set<AssessmentDto> assessmentDtos;

    @JsonCreator
    public LearningEvaluationDto(Set<AssessmentDto> assessmentDtos) {
        this.assessmentDtos = assessmentDtos;
    }

    @Override
    public LearningEvaluation intoDomainEntity(LearningEvaluationGenerationSchema<?> schema) {
        return LearningEvaluation.create(assessmentDtos.stream()
                .map(o -> Assessment.create(Feedback.of(o.getFeedbackText()), new ElementalRequirementId(o.getElementalRequirementId()), o.getMasteryPoints()))
                .collect(Collectors.toSet()));
    }
}

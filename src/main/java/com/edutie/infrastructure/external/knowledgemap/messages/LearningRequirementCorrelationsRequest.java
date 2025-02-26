package com.edutie.infrastructure.external.knowledgemap.messages;

import com.edutie.domain.core.education.learningrequirement.LearningSubject;

import java.util.Set;

public record LearningRequirementCorrelationsRequest(
        Set<LearningSubject> sourceRequirements,
        Set<LearningSubject> comparedLearningSubjects
) {
}

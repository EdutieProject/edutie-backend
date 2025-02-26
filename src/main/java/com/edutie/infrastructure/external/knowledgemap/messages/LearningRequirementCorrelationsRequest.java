package com.edutie.infrastructure.external.knowledgemap.messages;

import com.edutie.domain.core.education.learningsubject.LearningSubject;

import java.util.Set;

public record LearningRequirementCorrelationsRequest(
        Set<LearningSubject> sourceRequirements,
        Set<LearningSubject> comparedLearningSubjects
) {
}

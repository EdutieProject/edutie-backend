package com.edutie.backend.infrastructure.external.knowledgemap.messages;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;

import java.util.Set;

public record LearningRequirementCorrelationsRequest(
        Set<LearningRequirement> sourceRequirements,
        Set<LearningRequirement> comparedLearningRequirements
) {
}

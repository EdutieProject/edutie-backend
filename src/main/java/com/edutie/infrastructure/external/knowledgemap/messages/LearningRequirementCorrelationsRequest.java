package com.edutie.infrastructure.external.knowledgemap.messages;

import com.edutie.domain.core.education.learningrequirement.LearningRequirement;

import java.util.Set;

public record LearningRequirementCorrelationsRequest(
        Set<LearningRequirement> sourceRequirements,
        Set<LearningRequirement> comparedLearningRequirements
) {
}

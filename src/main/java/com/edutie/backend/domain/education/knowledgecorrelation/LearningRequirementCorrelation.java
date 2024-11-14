package com.edutie.backend.domain.education.knowledgecorrelation;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import lombok.Getter;
import lombok.Setter;

/**
 * Learning Requirement Correlation represents the correlation between the given learning requirement
 */
@Getter
@Setter
public class LearningRequirementCorrelation {
    private LearningRequirementId sourceLearningRequirementId;
    private LearningRequirementId correlatedLearningRequirementId;
    private Integer correlationFactor;

    public LearningRequirementCorrelation(LearningRequirementId sourceLearningRequirementId, LearningRequirementId correlatedLearningRequirementId, int correlationFactor) {
        this.sourceLearningRequirementId = sourceLearningRequirementId;
        this.correlatedLearningRequirementId = correlatedLearningRequirementId;
        this.correlationFactor = correlationFactor;
    }

    @Override
    public String toString() {
        return String.format("[Learning Requirement Correlation: {%s} -> {%s} | Factor: %s]",
                sourceLearningRequirementId, correlatedLearningRequirementId, correlationFactor);
    }
}

package com.edutie.backend.domain.education.knowledgecorrelation;

import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private int correlationFactor;

    @JsonCreator
    public LearningRequirementCorrelation(
            @JsonProperty("sourceLearningRequirementId") LearningRequirementId sourceLearningRequirementId,
            @JsonProperty("correlatedLearningRequirementId") LearningRequirementId correlatedLearningRequirementId,
            @JsonProperty("correlationFactor") int correlationFactor) {
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

package com.edutie.domain.core.education.knowledgesubject.knowledgecorrelation;

import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
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
    private LearningSubjectId sourceLearningSubjectId;
    private LearningSubjectId correlatedLearningSubjectId;
    private int correlationFactor;

    @JsonCreator
    public LearningRequirementCorrelation(
            @JsonProperty("sourceLearningRequirementId") LearningSubjectId sourceLearningSubjectId,
            @JsonProperty("correlatedLearningRequirementId") LearningSubjectId correlatedLearningSubjectId,
            @JsonProperty("correlationFactor") int correlationFactor) {
        this.sourceLearningSubjectId = sourceLearningSubjectId;
        this.correlatedLearningSubjectId = correlatedLearningSubjectId;
        this.correlationFactor = correlationFactor;
    }

    @Override
    public String toString() {
        return String.format("[Learning Requirement Correlation: {%s} -> {%s} | Factor: %s]",
                sourceLearningSubjectId, correlatedLearningSubjectId, correlationFactor);
    }
}

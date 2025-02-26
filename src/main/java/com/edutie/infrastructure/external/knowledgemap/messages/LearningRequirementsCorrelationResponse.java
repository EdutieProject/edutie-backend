package com.edutie.infrastructure.external.knowledgemap.messages;

import com.edutie.domain.core.education.knowledgesubject.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.infrastructure.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Set;

public record LearningRequirementsCorrelationResponse(
        @JsonValue Set<LearningRequirementCorrelation> learningRequirementCorrelations
) implements ExternalInfrastructureDto<Set<LearningRequirementCorrelation>, LearningRequirementCorrelationsRequest> {

    @JsonCreator
    public LearningRequirementsCorrelationResponse {
    }

    @Override
    public Set<LearningRequirementCorrelation> intoDomainEntity(LearningRequirementCorrelationsRequest learningRequirementCorrelationsRequest) {
        return learningRequirementCorrelations;
    }
}

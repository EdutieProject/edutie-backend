package com.edutie.backend.infrastructure.external.knowledgemap.messages;

import com.edutie.backend.domain.education.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.backend.infrastructure.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Set;

public record LearningRequirementsCorrelationResponse(
        @JsonValue Set<LearningRequirementCorrelation> learningRequirementCorrelations
) implements ExternalInfrastructureDto<Set<LearningRequirementCorrelation>, LearningRequirementCorrelationsRequest> {
    @Override
    public Set<LearningRequirementCorrelation> intoDomainEntity(LearningRequirementCorrelationsRequest learningRequirementCorrelationsRequest) {
        return learningRequirementCorrelations;
    }
}

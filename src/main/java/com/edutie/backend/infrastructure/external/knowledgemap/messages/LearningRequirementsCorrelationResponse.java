package com.edutie.backend.infrastructure.external.knowledgemap.messages;

import com.edutie.backend.domain.education.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.backend.infrastructure.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashSet;
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

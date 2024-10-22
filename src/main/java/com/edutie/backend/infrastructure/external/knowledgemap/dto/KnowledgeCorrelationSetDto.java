package com.edutie.backend.infrastructure.external.knowledgemap.dto;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.infrastructure.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Knowledge correlation DTO for creation of the Knowledge Correlation meta-entity
 */
public class KnowledgeCorrelationSetDto implements ExternalInfrastructureDto<Set<KnowledgeCorrelation>, Set<KnowledgeSubjectId>> {
    @JsonProperty
    Set<SingleKnowledgeCorrelationDto> knowledgeCorrelationDtos;

    @JsonCreator
    public KnowledgeCorrelationSetDto(Set<SingleKnowledgeCorrelationDto> knowledgeCorrelationDtos) {
        this.knowledgeCorrelationDtos = knowledgeCorrelationDtos;
    }

    @Override
    public Set<KnowledgeCorrelation> intoDomainEntity(Set<KnowledgeSubjectId> unused) {
        return knowledgeCorrelationDtos.stream().map(o -> new KnowledgeCorrelation(
                new KnowledgeSubjectId(o.getSourceSubjectId()),
                new KnowledgeSubjectId(o.getCorrelatedSubjectId()),
                o.getCorrelationFactor()
        )).collect(Collectors.toSet());
    }
}

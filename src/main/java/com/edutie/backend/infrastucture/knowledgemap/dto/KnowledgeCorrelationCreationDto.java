package com.edutie.backend.infrastucture.knowledgemap.dto;

import com.edutie.backend.domain.education.knowledgecorrelation.KnowledgeCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Knowledge correlation DTO for creation of the Knowledge Correlation meta-entity
 */
public class KnowledgeCorrelationCreationDto {
    @JsonProperty
    private UUID correlatedSubjectId;
    @JsonProperty
    private UUID sourceSubjectId;
    @JsonProperty
    private Integer correlationFactor;

    public KnowledgeCorrelation intoKnowledgeCorrelation() {
        return new KnowledgeCorrelation(
                new KnowledgeSubjectId(sourceSubjectId),
                new KnowledgeSubjectId(correlatedSubjectId),
                correlationFactor
        );
    }
}

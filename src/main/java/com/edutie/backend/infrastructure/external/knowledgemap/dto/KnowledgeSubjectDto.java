package com.edutie.backend.infrastructure.external.knowledgemap.dto;

import com.edutie.backend.domain.education.knowledgesubject.KnowledgeSubject;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.infrastructure.external.common.dto.ExternalInfrastructureDto;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KnowledgeSubjectDto implements ExternalInfrastructureDto<KnowledgeSubject, Void> {
    @JsonProperty
    String knowledgeSubjectId;
    @JsonProperty
    String title;

    @Override
    public KnowledgeSubject intoDomainEntity(Void unused) {
        return KnowledgeSubject.create(
                new KnowledgeSubjectId(knowledgeSubjectId),
                title
        );
    }
}

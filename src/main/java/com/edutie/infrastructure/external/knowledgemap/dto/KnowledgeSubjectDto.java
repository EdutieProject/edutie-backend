package com.edutie.infrastructure.external.knowledgemap.dto;

import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.infrastructure.external.common.dto.ExternalInfrastructureDto;
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

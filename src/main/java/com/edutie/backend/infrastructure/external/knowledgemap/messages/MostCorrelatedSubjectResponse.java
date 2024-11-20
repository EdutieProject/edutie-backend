package com.edutie.backend.infrastructure.external.knowledgemap.messages;

import com.edutie.backend.domain.education.knowledgesubject.KnowledgeSubject;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.infrastructure.external.common.dto.ExternalInfrastructureDto;

public record MostCorrelatedSubjectResponse(
        String id,
        String title
) implements ExternalInfrastructureDto<KnowledgeSubject, MostCorrelatedSubjectRequest> {
    @Override
    public KnowledgeSubject intoDomainEntity(MostCorrelatedSubjectRequest mostCorrelatedSubjectRequest) {
        return KnowledgeSubject.create(
                new KnowledgeSubjectId(id),
                title
        );
    }
}

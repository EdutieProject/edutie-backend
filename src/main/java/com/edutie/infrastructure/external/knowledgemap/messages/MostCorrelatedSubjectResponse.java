package com.edutie.infrastructure.external.knowledgemap.messages;

import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.infrastructure.external.common.dto.ExternalInfrastructureDto;

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

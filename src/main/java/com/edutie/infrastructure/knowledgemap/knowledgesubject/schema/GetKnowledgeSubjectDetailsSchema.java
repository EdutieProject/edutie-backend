package com.edutie.infrastructure.knowledgemap.knowledgesubject.schema;

import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;

public record GetKnowledgeSubjectDetailsSchema(
        KnowledgeSubjectId knowledgeSubjectId
) {
}

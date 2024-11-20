package com.edutie.backend.infrastructure.external.knowledgemap.messages;

import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;

public record MostCorrelatedSubjectRequest(KnowledgeSubjectId knowledgeSubjectId) {
}

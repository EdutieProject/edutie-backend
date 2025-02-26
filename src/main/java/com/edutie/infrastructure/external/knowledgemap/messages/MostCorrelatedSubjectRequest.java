package com.edutie.infrastructure.external.knowledgemap.messages;

import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;

public record MostCorrelatedSubjectRequest(KnowledgeSubjectId knowledgeSubjectId) {
}

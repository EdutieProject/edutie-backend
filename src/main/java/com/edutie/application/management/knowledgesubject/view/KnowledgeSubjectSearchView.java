package com.edutie.application.management.knowledgesubject.view;

import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubjectReference;

public record KnowledgeSubjectSearchView(
        KnowledgeSubjectReference knowledgeSubjectReference,
        String title,
        String description
) {
}

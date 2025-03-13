package com.edutie.domain.core.education.knowledgesubject.view;

import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubjectReference;

public record KnowledgeSubjectDetailsView(
        KnowledgeSubjectReference knowledgeSubjectReference,
        String title,
        String description
) {
}

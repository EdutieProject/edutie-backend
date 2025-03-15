package com.edutie.infrastructure.knowledgemap.knowledgesubject.schema;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.knowledgesubject.identities.KnowledgeSubjectId;

public record GetKnowledgeContextSchema(
        KnowledgeSubjectId knowledgeSubjectId,
        PromptFragment promptFragment
) {
}

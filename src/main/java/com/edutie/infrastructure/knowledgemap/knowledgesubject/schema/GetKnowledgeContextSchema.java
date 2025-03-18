package com.edutie.infrastructure.knowledgemap.knowledgesubject.schema;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;

public record GetKnowledgeContextSchema(
        KnowledgeOrigin knowledgeOrigin,
        PromptFragment studentObjective
) {
}

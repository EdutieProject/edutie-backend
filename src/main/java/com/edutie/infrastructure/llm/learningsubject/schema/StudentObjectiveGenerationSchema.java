package com.edutie.infrastructure.llm.learningsubject.schema;

import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.infrastructure.llm.common.schema.GenerationSchema;

public record StudentObjectiveGenerationSchema(
        String objectiveTitle,
        KnowledgeOrigin knowledgeOrigin
) implements GenerationSchema {
}

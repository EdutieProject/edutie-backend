package com.edutie.infrastructure.knowledgemap.learningsubject.schema;

import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;
import com.edutie.infrastructure.knowledgemap.common.generative.KnowledgeMapGenerationSchema;

public record StudentObjectiveGenerationSchema(
        String objectiveTitle,
        KnowledgeOrigin knowledgeOrigin
) implements KnowledgeMapGenerationSchema {
}

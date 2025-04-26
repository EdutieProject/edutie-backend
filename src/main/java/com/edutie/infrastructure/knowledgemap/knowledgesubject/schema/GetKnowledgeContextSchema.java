package com.edutie.infrastructure.knowledgemap.knowledgesubject.schema;

import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningsubject.entities.KnowledgeOrigin;

public record GetKnowledgeContextSchema(
        KnowledgeOrigin knowledgeOrigin,
        ElementalRequirement elementalRequirement
) {
}

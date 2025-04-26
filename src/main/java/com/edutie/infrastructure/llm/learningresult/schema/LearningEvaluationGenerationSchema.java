package com.edutie.infrastructure.llm.learningresult.schema;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.learning.learningresult.entities.submission.base.SolutionSubmission;
import com.edutie.infrastructure.llm.common.schema.LlmGenerationSchema;

import java.util.Set;

public record LearningEvaluationGenerationSchema<T extends SolutionSubmission>(
        T solutionSubmission,
        PromptFragment knowledgeContext,
        Set<ElementalRequirement> elementalRequirements
) implements LlmGenerationSchema {
}

package com.edutie.infrastructure.llm.learningexperience.schema;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;
import com.edutie.domain.core.personalization.learningexperience.contentadjustment.base.ContentAdjustmentStrategy;
import com.edutie.infrastructure.llm.common.schema.LlmGenerationSchema;

import java.util.Set;

public record LearningExperienceGenerationSchema(
        PromptFragment knowledgeContext,
        Class<? extends Activity> activityClass,
        ElementalRequirement elementalRequirement,
        Set<ContentAdjustmentStrategy<?, ?>> contentAdjustmentStrategies
) implements LlmGenerationSchema {
}

package com.edutie.infrastructure.llm.learningexperience.schema;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;
import com.edutie.domain.core.personalization.common.PersonalizationRule;
import com.edutie.infrastructure.llm.common.schema.LlmGenerationSchema;

import java.util.Set;

public record LearningExperienceGenerationSchema(
        PromptFragment knowledgeContext,
        String activityCode,
        ElementalRequirement elementalRequirement,
        Set<PersonalizationRule<?>> personalizationRules
) implements LlmGenerationSchema {

    public LearningExperienceGenerationSchema(PromptFragment knowledgeContext,
                                              Class<? extends Activity> activityClass,
                                              ElementalRequirement elementalRequirement,
                                              Set<PersonalizationRule<?>> personalizationRules) {
        this(knowledgeContext, activityClass.getSimpleName(), elementalRequirement, personalizationRules);
    }
}

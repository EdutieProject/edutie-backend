package com.edutie.infrastructure.llm.learningexperience.schema;

import com.edutie.domain.core.common.generationprompt.PromptFragment;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.common.PersonalizationRule;
import com.edutie.infrastructure.llm.common.schema.LlmGenerationSchema;

import java.util.Set;

public record LearningExperienceGenerationSchema(
        com.edutie.domain.core.learning.student.Student student, String activityCode,
        ElementalRequirement elementalRequirement, Set<PersonalizationRule<?>> personalizationRules, PromptFragment knowledgeContext
) implements LlmGenerationSchema {

    public LearningExperienceGenerationSchema(Student student,
                                              PromptFragment knowledgeContext,
                                              Class<? extends Activity> activityClass,
                                              ElementalRequirement elementalRequirement,
                                              Set<PersonalizationRule<?>> personalizationRules) {
        this(student, activityClass.getSimpleName(), elementalRequirement, personalizationRules, knowledgeContext);
    }
}

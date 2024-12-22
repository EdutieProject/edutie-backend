package com.edutie.backend.domainservice.personalization.learningresource.schema;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.personalization.learningresourcedefinition.StaticLearningResourceDefinition;

public record AdditionalInstructions(
        PromptFragment theoryInstructions,
        PromptFragment activityInstructions
) {

    public static AdditionalInstructions fromDefinition(StaticLearningResourceDefinition definition) {
        return new AdditionalInstructions(
                definition.getTheoryDetails().getOverviewDescription(),
                definition.getActivityDetails().getExerciseDescription()
            );
    }
}

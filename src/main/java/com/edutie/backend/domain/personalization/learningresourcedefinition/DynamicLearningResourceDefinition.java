package com.edutie.backend.domain.personalization.learningresourcedefinition;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter(AccessLevel.PRIVATE)
@Getter
public class DynamicLearningResourceDefinition extends LearningResourceDefinitionBase {
    private PromptFragment context;

    /**
     * Creates a random-fact dynamic LRD
     *
     * @param context              random fact as string
     * @param learningRequirements learning requirement set
     * @return Dynamic Learning Resource Definition
     */
    public static DynamicLearningResourceDefinition create(PromptFragment context, Set<LearningRequirement> learningRequirements) {
        DynamicLearningResourceDefinition dynamicLearningResourceDefinition = new DynamicLearningResourceDefinition();
        dynamicLearningResourceDefinition.setId(new LearningResourceDefinitionId());
        dynamicLearningResourceDefinition.setContext(context);
        learningRequirements.forEach(dynamicLearningResourceDefinition::addLearningRequirement);
        return dynamicLearningResourceDefinition;
    }

    @Override
    public DefinitionType getDefinitionType() {
        return DefinitionType.DYNAMIC;
    }
}

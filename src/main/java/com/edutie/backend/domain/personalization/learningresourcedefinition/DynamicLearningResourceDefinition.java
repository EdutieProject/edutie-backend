package com.edutie.backend.domain.personalization.learningresourcedefinition;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.valueobjects.DynamicContext;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter(AccessLevel.PRIVATE)
@Getter
public class DynamicLearningResourceDefinition extends LearningResourceDefinitionBase {
    private DynamicContext context;

    /**
     * Creates a random-fact dynamic LRD
     *
     * @param context              random fact as string
     * @param learningRequirements learning requirement set
     * @return Dynamic Learning Resource Definition
     */
    public static DynamicLearningResourceDefinition create(DynamicContext context, Set<LearningRequirement> learningRequirements) {
        DynamicLearningResourceDefinition dynamicLearningResourceDefinition = new DynamicLearningResourceDefinition();
        dynamicLearningResourceDefinition.setId(new LearningResourceDefinitionId());
        dynamicLearningResourceDefinition.setContext(context); // adjust when other dynamic types are introduced
        learningRequirements.forEach(dynamicLearningResourceDefinition::addLearningRequirement);
        return dynamicLearningResourceDefinition;
    }

    @Override
    public DefinitionType getDefinitionType() {
        return DefinitionType.DYNAMIC;
    }
}

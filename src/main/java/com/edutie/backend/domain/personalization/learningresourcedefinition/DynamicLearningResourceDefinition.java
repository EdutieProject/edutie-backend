package com.edutie.backend.domain.personalization.learningresourcedefinition;

import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.base.LearningResourceDefinitionBase;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.ActivityDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.entities.TheoryDetails;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;

import java.util.Set;

public class DynamicLearningResourceDefinition extends LearningResourceDefinitionBase {

    /**
     * Creates a random-fact dynamic LRD
     * @param randomFact random fact as string
     * @param learningRequirements learning requirement set
     * @return Dynamic Learning Resource Definition
     */
    public static DynamicLearningResourceDefinition createRandomFact(String randomFact, Set<LearningRequirement> learningRequirements) {
        DynamicLearningResourceDefinition dynamicLearningResourceDefinition = new DynamicLearningResourceDefinition();
        dynamicLearningResourceDefinition.setId(new LearningResourceDefinitionId());
        dynamicLearningResourceDefinition.setTheoryDetails(TheoryDetails.create(PromptFragment.empty(), PromptFragment.empty()));
        dynamicLearningResourceDefinition.setActivityDetails(ActivityDetails.create(PromptFragment.empty(), PromptFragment.of(String.format("""
                Exercise must be related to the provided random fact:
                <random-fact>%s</random-fact>
                Exercise should utilize the provided data and utilize it to create an exercise in a creative way.
                All problems in this exercise should refer to the random fact and similar topics.
                """, randomFact))));
        learningRequirements.forEach(dynamicLearningResourceDefinition::addLearningRequirement);
        return dynamicLearningResourceDefinition;
    }

    @Override
    public DefinitionType getDefinitionType() {
        return DefinitionType.DYNAMIC;
    }
}

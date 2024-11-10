package com.edutie.backend.domain.personalization.rule.base;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PersonalizationStrategy<TPersonalizationContext, TRule extends PersonalizationRule<TPersonalizationContext>> {

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param learningRequirements learning requirements to consider
     * @param pastPerformance      learning history to consider when deciding qualification
     * @return Optional Personalization Rule
     */
    Optional<TRule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance);
}

package com.edutie.backend.domain.personalization.strategy;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationStrategy;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Personalization strategy for refreshing the knowledge of given elemental req
 */
@Component
public class RefreshStrategy implements PersonalizationStrategy<ElementalRequirement, RefreshStrategy.RefreshRule> {
    private final int REQUIRED_STREAK_SIZE = 3;

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param learningRequirements learning requirements to consider
     * @param pastPerformance      learning history to consider when deciding qualification
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<RefreshRule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance) {
        return Optional.empty();
    }

    public static class RefreshRule extends PersonalizationRule<ElementalRequirement> {
        public RefreshRule(ElementalRequirement context) {
            super(context);
        }
    }
}

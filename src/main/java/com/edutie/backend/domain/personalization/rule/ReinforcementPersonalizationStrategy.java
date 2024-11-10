package com.edutie.backend.domain.personalization.rule;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Reinforcement strategy for ensuring that previously learned content is still understood.
 */
@Component
public class ReinforcementPersonalizationStrategy implements PersonalizationStrategy<ElementalRequirement, ReinforcementPersonalizationStrategy.ReinforcementRule> {


    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param learningRequirements learning requirements to consider
     * @param pastPerformance      learning history to consider when deciding qualification
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<ReinforcementRule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance) {
        return Optional.empty();
    }

    public static class ReinforcementRule extends PersonalizationRule<ElementalRequirement> {
        public ReinforcementRule(ElementalRequirement context) {
            super(context);
        }
    }

}

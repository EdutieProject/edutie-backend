package com.edutie.backend.domain.personalization.rule;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * A personalization strategy for recommending additional learning requirements.
 */
@Component
public class RecommendationPersonalizationStrategy implements PersonalizationStrategy<LearningRequirement, RecommendationPersonalizationStrategy.Rule> {


    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param learningRequirements learning requirements to consider
     * @param pastPerformance      learning history to consider when deciding qualification
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<Rule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance) {
        return Optional.empty();
    }

    public static class Rule extends PersonalizationRule<LearningRequirement> {
        public Rule(LearningRequirement context) {
            super(context);
        }
    }
}



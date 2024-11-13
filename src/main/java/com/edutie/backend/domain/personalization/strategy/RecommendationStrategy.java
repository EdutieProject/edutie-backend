package com.edutie.backend.domain.personalization.strategy;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.backend.domain.personalization.student.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * A personalization strategy for recommending additional learning requirements.
 */
@Component
public class RecommendationStrategy implements PersonalizationStrategy<LearningRequirement, RecommendationStrategy.RecommendationRule> {

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param student
     * @param learningRequirements learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<RecommendationRule> qualifyRule(Student student, Set<LearningRequirement> learningRequirements) {
        return Optional.empty();
    }

    public static class RecommendationRule extends PersonalizationRule<LearningRequirement> {
        public RecommendationRule(LearningRequirement context) {
            super(context);
        }
    }
}



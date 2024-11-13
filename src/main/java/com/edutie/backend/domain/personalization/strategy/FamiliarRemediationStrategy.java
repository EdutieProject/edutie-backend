package com.edutie.backend.domain.personalization.strategy;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.backend.domain.personalization.student.Student;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * Remediation personalization strategy for remediating the content based on the knowledge of
 * a strongly correlated subject that is well understood.
 */
@Component
public class FamiliarRemediationStrategy implements PersonalizationStrategy<Feedback, FamiliarRemediationStrategy.FamiliarRemediationRule> {

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param student
     * @param learningRequirements learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<FamiliarRemediationRule> qualifyRule(Student student, Set<LearningRequirement> learningRequirements) {
        return Optional.empty();
    }

    public static class FamiliarRemediationRule extends PersonalizationRule<Feedback> {

        public FamiliarRemediationRule(Feedback context) {
            super(context);
        }
    }
}

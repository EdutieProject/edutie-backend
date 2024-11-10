package com.edutie.backend.domain.personalization.rule;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Remediation personalization strategy for remediating the content based on the previous feedback.
 */
@Component
public class RemediationPersonalizationStrategy implements PersonalizationStrategy<Feedback, RemediationPersonalizationStrategy.RemediationRule> {


    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param learningRequirements learning requirements to consider
     * @param pastPerformance      learning history to consider when deciding qualification
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<RemediationRule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance) {
        return Optional.empty();
    }

    public static class RemediationRule extends PersonalizationRule<Feedback> {

        public RemediationRule(Feedback context) {
            super(context);
        }
    }
}

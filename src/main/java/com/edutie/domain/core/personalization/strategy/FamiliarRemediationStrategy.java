package com.edutie.domain.core.personalization.strategy;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * Remediation personalization strategy for remediating the content based on the knowledge of
 * a strongly correlated subject that is well understood.
 */
@Component
@RequiredArgsConstructor
public class FamiliarRemediationStrategy implements PersonalizationStrategy<Feedback, FamiliarRemediationStrategy.FamiliarRemediationRule> {

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param student          student
     * @param learningSubjects learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<FamiliarRemediationRule> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
        return Optional.empty();
    }

    public static class FamiliarRemediationRule extends PersonalizationRule<Feedback> {

        public FamiliarRemediationRule(Feedback context) {
            super(context);
        }
    }
}

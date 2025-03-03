package com.edutie.domain.core.personalization.strategy;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * Remediation personalization strategy for remediating the content based on the previous feedback.
 */
@Component
@RequiredArgsConstructor
public class FeedbackRemediationStrategy implements PersonalizationStrategy<Feedback, FeedbackRemediationStrategy.FeedbackRemediationRule> {
    private final LearningResultPersistence learningResultPersistence;

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param student          student
     * @param learningSubjects learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<FeedbackRemediationRule> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
        return Optional.empty();
    }

    public static class FeedbackRemediationRule extends PersonalizationRule<Feedback> {

        public FeedbackRemediationRule(Feedback context) {
            super(context);
        }
    }
}

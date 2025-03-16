package com.edutie.domain.core.personalization.learningexperience.contentadjustment;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.common.PersonalizationRuleBase;
import com.edutie.domain.core.personalization.learningexperience.contentadjustment.base.ContentAdjustmentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * Remediation personalization strategy for remediating the content based on the previous feedback.
 */
@Component
@RequiredArgsConstructor
public class FeedbackRemediationStrategy implements ContentAdjustmentStrategy<Feedback, FeedbackRemediationStrategy.FeedbackRemediationRuleBase> {
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
    public Optional<FeedbackRemediationRuleBase> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
        return Optional.empty();
    }

    public static class FeedbackRemediationRuleBase extends PersonalizationRuleBase<Feedback> {

        public FeedbackRemediationRuleBase(Feedback context) {
            super(context);
        }
    }
}

package com.edutie.domain.core.personalization.strategy;

import com.edutie.domain.core.common.base.AuditableEntityBase;
import com.edutie.domain.core.education.learningrequirement.LearningSubject;
import com.edutie.domain.core.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.Assessment;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.domain.core.learning.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
     * @param student              student
     * @param learningSubjects learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<FeedbackRemediationRule> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
        List<LearningResult> pastPerformance = student.getLatestLearningResults(learningResultPersistence);
        Set<LearningRequirementId> learningRequirementIds = learningSubjects.stream().map(LearningSubject::getId).collect(Collectors.toSet());
        List<LearningResult> consideredLearningResults = pastPerformance.stream().filter(o -> o.getLearningRequirementIds().stream().anyMatch(learningRequirementIds::contains)).toList();
        if (consideredLearningResults.isEmpty() || consideredLearningResults.stream().allMatch(o -> o.getAverageGrade().greaterThanOrEqual(Grade.of(3))))
            return Optional.empty();
        LearningResult latestResult = consideredLearningResults.stream().max(Comparator.comparing(AuditableEntityBase::getCreatedOn)).get();
        Feedback latestFeedback = latestResult.getAssessments().stream().min(Comparator.comparing(Assessment::getGrade)).get().getFeedback();
        return Optional.of(new FeedbackRemediationRule(latestFeedback));
    }

    public static class FeedbackRemediationRule extends PersonalizationRule<Feedback> {

        public FeedbackRemediationRule(Feedback context) {
            super(context);
        }
    }
}

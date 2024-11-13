package com.edutie.backend.domain.personalization.strategy;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.backend.domain.personalization.student.Student;
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
     * @param student
     * @param learningRequirements learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<FeedbackRemediationRule> qualifyRule(Student student, Set<LearningRequirement> learningRequirements) {
        List<LearningResult> pastPerformance = student.getLatestLearningResults(learningResultPersistence);
        Set<LearningRequirementId> learningRequirementIds = learningRequirements.stream().map(LearningRequirement::getId).collect(Collectors.toSet());
        List<LearningResult> consideredLearningResults = pastPerformance.stream().filter(o -> o.getLearningRequirementIds().stream().anyMatch(learningRequirementIds::contains)).toList();
        if (consideredLearningResults.isEmpty() || consideredLearningResults.stream().allMatch(o -> o.getAverageGrade().greaterThanOrEqual(Grade.of(3))))
            return Optional.empty();
        Feedback latestFeedback = consideredLearningResults.stream().max(Comparator.comparing(AuditableEntityBase::getCreatedOn)).get().getFeedback();
        return Optional.of(new FeedbackRemediationRule(latestFeedback));
    }

    public static class FeedbackRemediationRule extends PersonalizationRule<Feedback> {

        public FeedbackRemediationRule(Feedback context) {
            super(context);
        }
    }
}

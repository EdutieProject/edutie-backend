package com.edutie.backend.domain.personalization.strategy;

import com.edutie.backend.domain.education.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.infrastructure.external.knowledgemap.KnowledgeMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Remediation personalization strategy for remediating the content based on the knowledge of
 * a strongly correlated subject that is well understood.
 */
@Component
@RequiredArgsConstructor
public class FamiliarRemediationStrategy implements PersonalizationStrategy<Feedback, FamiliarRemediationStrategy.FamiliarRemediationRule> {
    private final KnowledgeMapService knowledgeMapService;
    private final LearningResultPersistence learningResultPersistence;
    private static final int CORRELATION_MEASURED_RESULTS_AMOUNT = 5;

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param student              student
     * @param learningRequirements learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<FamiliarRemediationRule> qualifyRule(Student student, Set<LearningRequirement> learningRequirements) {
        List<LearningResult> pastPerformance = student.getLatestLearningResults(learningResultPersistence);
        if (pastPerformance.isEmpty())
            return Optional.empty();
        List<LearningResult> topResults = pastPerformance.stream()
                .filter(o -> o.getAverageGrade().greaterThanOrEqual(Grade.of(5)))
                .limit(CORRELATION_MEASURED_RESULTS_AMOUNT).toList();

        Optional<LearningResult> highestCorrelatedResult = topResults.stream()
                .max(Comparator.comparing(result -> {
                    Set<LearningRequirementCorrelation> learningRequirementsCorrelation = knowledgeMapService
                            .getLearningRequirementCorrelations(learningRequirements, result.getAssociatedLearningRequirements()).getValue();
                    return learningRequirementsCorrelation.stream()
                            .mapToDouble(LearningRequirementCorrelation::getCorrelationFactor)
                            .average()
                            .orElse(Double.MIN_VALUE); // Default if no correlations are present
                }));

        return highestCorrelatedResult.map(o -> new FamiliarRemediationRule(o.getFeedback()));
    }

    public static class FamiliarRemediationRule extends PersonalizationRule<Feedback> {

        public FamiliarRemediationRule(Feedback context) {
            super(context);
        }
    }
}

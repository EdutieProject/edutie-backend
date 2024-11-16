package com.edutie.backend.domain.personalization.strategy;

import com.edutie.backend.domain.education.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.backend.domain.education.knowledgesubject.KnowledgeSubject;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
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
 * A personalization strategy for recommending additional learning requirements.
 */
@Component
@RequiredArgsConstructor
public class RecommendationStrategy implements PersonalizationStrategy<KnowledgeSubject, RecommendationStrategy.RecommendationRule> {
    private final KnowledgeMapService knowledgeMapService;
    private final LearningResultPersistence learningResultPersistence;

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param student              student
     * @param learningRequirements learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<RecommendationRule> qualifyRule(Student student, Set<LearningRequirement> learningRequirements) {
        List<LearningResult> pastPerformance = student.getLatestLearningResults(learningResultPersistence);
        List<LearningResult> topResults = pastPerformance.stream()
                .filter(o -> o.getAverageGrade().greaterThanOrEqual(Grade.of(5)))
                .toList();

        LearningResult randomResult = topResults.get((int) Math.floor(Math.random() * topResults.size()));

        Optional<LearningRequirement> highestCorrelationRequirement = randomResult.getAssociatedLearningRequirements().stream()
                .max(Comparator.comparing(requirement -> {
                    Set<LearningRequirementCorrelation> learningRequirementsCorrelation = knowledgeMapService
                            .getLearningRequirementCorrelations(learningRequirements, Set.of(requirement)).getValue();
                    return learningRequirementsCorrelation.stream()
                            .mapToDouble(LearningRequirementCorrelation::getCorrelationFactor)
                            .average()
                            .orElse(Double.MIN_VALUE); // Default if no correlations are present
                }));
        if (highestCorrelationRequirement.isEmpty())
            return Optional.empty();

        KnowledgeSubject knowledgeSubject = knowledgeMapService.getAssociatedKnowledgeSubject(highestCorrelationRequirement.get().getKnowledgeSubjectId()).getValue();

        return Optional.of(new RecommendationRule(knowledgeSubject));
    }

    public static class RecommendationRule extends PersonalizationRule<KnowledgeSubject> {
        public RecommendationRule(KnowledgeSubject context) {
            super(context);
        }
    }
}



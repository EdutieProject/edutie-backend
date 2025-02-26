package com.edutie.domain.core.personalization.strategy;

import com.edutie.domain.core.education.knowledgesubject.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.domain.core.education.knowledgesubject.KnowledgeSubject;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.infrastructure.external.knowledgemap.KnowledgeMapService;
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
     * @param learningSubjects learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<RecommendationRule> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
        List<LearningResult> pastPerformance = student.getLatestLearningResults(learningResultPersistence);
        List<LearningResult> topResults = pastPerformance.stream()
                .filter(o -> o.getAverageGrade().greaterThanOrEqual(Grade.of(5)))
                .toList();

        if (topResults.isEmpty())
            return Optional.empty();

        LearningResult randomResult = topResults.get((int) Math.floor(Math.random() * topResults.size()));

        Optional<LearningSubject> highestCorrelationRequirement = randomResult.getAssociatedLearningRequirements().size() == 1 ?
                randomResult.getAssociatedLearningRequirements().stream().findFirst() :
                randomResult.getAssociatedLearningRequirements().stream()
                        .max(Comparator.comparing(requirement -> {
                            Set<LearningRequirementCorrelation> learningRequirementsCorrelation = knowledgeMapService
                                    .getLearningRequirementCorrelations(learningSubjects, Set.of(requirement)).getValue();
                            return learningRequirementsCorrelation.stream()
                                    .mapToDouble(LearningRequirementCorrelation::getCorrelationFactor)
                                    .average()
                                    .orElse(Double.MIN_VALUE); // Default if no correlations are present
                        }));

        if (highestCorrelationRequirement.isEmpty())
            return Optional.empty();

        KnowledgeSubject knowledgeSubject = knowledgeMapService.getMostCorrelatedKnowledgeSubject(highestCorrelationRequirement.get().getKnowledgeSubjectId()).getValue();

        return Optional.of(new RecommendationRule(knowledgeSubject));
    }

    public static class RecommendationRule extends PersonalizationRule<KnowledgeSubject> {
        public RecommendationRule(KnowledgeSubject context) {
            super(context);
        }
    }
}



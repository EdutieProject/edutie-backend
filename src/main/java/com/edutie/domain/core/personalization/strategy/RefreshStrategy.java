package com.edutie.domain.core.personalization.strategy;

import com.edutie.domain.core.education.knowledgesubject.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.persistence.LearningResultPersistence;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.infrastructure.external.knowledgemap.KnowledgeMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Personalization strategy for refreshing the knowledge of given elemental requirements.
 */
@Component
@RequiredArgsConstructor
public class RefreshStrategy implements PersonalizationStrategy<ElementalRequirement, RefreshStrategy.RefreshRule> {
    private final KnowledgeMapService knowledgeMapService;
    private final LearningResultPersistence learningResultPersistence;
    private static final int REQUIRED_STREAK_SIZE = 3;
    private static final int MAX_STREAK_COUNT = 3;

    @Override
    public Optional<RefreshRule> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
        List<LearningResult> pastPerformance = student.getLatestLearningResults(learningResultPersistence);
        if (pastPerformance.isEmpty())
            return Optional.empty();
        // get the learning streaks happening in the past
        List<Set<LearningResult>> learningStreaks = new ArrayList<>();
        Set<LearningResult> currentStreak = new HashSet<>();
        for (int i = 1; i < pastPerformance.size(); i++) {
            LearningResult previousLearningResult = pastPerformance.get(i - 1);
            LearningResult currentLearningResult = pastPerformance.get(i);
            if (currentLearningResult.hasOverlappingLearningRequirements(previousLearningResult)) {
                currentStreak.add(previousLearningResult);
                currentStreak.add(currentLearningResult);
                continue;
            }
            // below code happens only when the learning streak has ended
            if (currentStreak.size() >= REQUIRED_STREAK_SIZE) {
                learningStreaks.add(new HashSet<>(currentStreak));
            }
            currentStreak.clear();
        }
        Set<LearningSubject> learningRequirementsToRefreshes = learningStreaks
                .stream().limit(MAX_STREAK_COUNT)
                .flatMap(o -> o.stream().filter(x -> x.getAverageGrade().greaterThanOrEqual(Grade.of(3))))
                .flatMap(o -> o.getAssociatedLearningRequirements().stream())
                .collect(Collectors.toSet());
        if (learningRequirementsToRefreshes.isEmpty())
            return Optional.empty();

        // measure the correlations of requirements to be refreshed
        Set<LearningRequirementCorrelation> learningRequirementCorrelations = knowledgeMapService
                .getLearningRequirementCorrelations(learningSubjects, learningRequirementsToRefreshes).getValue();
        LearningRequirementCorrelation mostCorrelatedCorrelation = learningRequirementCorrelations.stream()
                .max(Comparator.comparing(LearningRequirementCorrelation::getCorrelationFactor)).get();

        LearningSubject learningSubjectToRefresh = learningRequirementsToRefreshes.stream()
                .filter(o -> o.getId().equals(mostCorrelatedCorrelation.getCorrelatedLearningRequirementId()))
                .toList().getFirst();

        ElementalRequirement elementalRequirementToRefresh = learningSubjectToRefresh.calculateQualifiedElementalRequirements(
                student.getLearningHistoryByKnowledgeSubject(learningResultPersistence, learningSubjectToRefresh.getKnowledgeSubjectId()),
                1
        ).stream().max(Comparator.comparingInt(ElementalRequirement::getOrdinal)).get();

        return Optional.of(new RefreshRule(elementalRequirementToRefresh));
    }

    public static class RefreshRule extends PersonalizationRule<ElementalRequirement> {
        public RefreshRule(ElementalRequirement context) {
            super(context);
        }
    }
}

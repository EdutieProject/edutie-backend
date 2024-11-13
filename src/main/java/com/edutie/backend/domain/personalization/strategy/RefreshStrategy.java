package com.edutie.backend.domain.personalization.strategy;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.knowledgecorrelation.LearningRequirementCorrelation;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.backend.infrastructure.external.knowledgemap.KnowledgeMapService;
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
    private static final int REQUIRED_STREAK_COUNT = 3;

    @Override
    public Optional<RefreshRule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance) {
        if (pastPerformance.isEmpty()) {
            return Optional.empty();
        }

        pastPerformance.sort(Comparator.comparing(AuditableEntityBase::getCreatedOn));
        List<List<LearningResult>> learningStreaks = getLearningStreaks(pastPerformance, learningRequirements);

        if (learningStreaks.size() < REQUIRED_STREAK_COUNT) {
            return Optional.empty();
        }

        Set<LearningRequirement> learningRequirementsToRefresh = getLearningRequirementsToRefresh(learningStreaks);
        LearningRequirement learningRequirementToRefresh = findMostCorrelatedRequirement(learningRequirements, learningRequirementsToRefresh);

        if (learningRequirementToRefresh == null) {
            return Optional.empty();
        }

        ElementalRequirement elementalRequirementToRefresh = findQualifiedElementalRequirement(pastPerformance, learningRequirementToRefresh);

        return Optional.of(new RefreshRule(elementalRequirementToRefresh));
    }

    /**
     * Collects learning streaks from the provided past performances.
     */
    private List<List<LearningResult>> getLearningStreaks(List<LearningResult> pastPerformance, Set<LearningRequirement> learningRequirements) {
        List<List<LearningResult>> learningStreaks = new ArrayList<>();
        List<LearningResult> currentStreak = new ArrayList<>();
        Set<LearningRequirementId> requirementIdsInCurrentResult = new HashSet<>();

        for (int i = 1; i < pastPerformance.size(); i++) {
            requirementIdsInCurrentResult.clear();
            requirementIdsInCurrentResult.addAll(pastPerformance.get(i - 1).getLearningRequirementIds());

            LearningResult currentResult = pastPerformance.get(i);
            if (currentResult.getLearningRequirementIds().stream().anyMatch(requirementIdsInCurrentResult::contains)) {
                currentStreak.add(currentResult);

                if (currentStreak.size() >= REQUIRED_STREAK_SIZE) {
                    learningStreaks.add(new ArrayList<>(currentStreak));
                    currentStreak.clear();
                }
            } else {
                currentStreak.clear();
            }

            if (learningStreaks.size() >= REQUIRED_STREAK_COUNT) {
                break;
            }
        }

        return learningStreaks;
    }

    /**
     * Collects the unique learning requirements from the list of learning streaks.
     */
    private Set<LearningRequirement> getLearningRequirementsToRefresh(List<List<LearningResult>> learningStreaks) {
        return learningStreaks.stream()
                .flatMap(Collection::stream)
                .flatMap(result -> result.getAssociatedLearningRequirements().stream())
                .collect(Collectors.toSet());
    }

    /**
     * Finds the most correlated learning requirement from the learning requirements to be refreshed.
     */
    private LearningRequirement findMostCorrelatedRequirement(Set<LearningRequirement> baseRequirements, Set<LearningRequirement> targetRequirements) {
        return knowledgeMapService.getLearningRequirementCorrelations(baseRequirements, targetRequirements).getValue()
                .stream()
                .max(Comparator.comparing(LearningRequirementCorrelation::getCorrelationFactor))
                .map(LearningRequirementCorrelation::getCorrelatedLearningRequirementId)
                .flatMap(correlatedId -> targetRequirements.stream()
                        .filter(requirement -> requirement.getId().equals(correlatedId))
                        .findFirst())
                .orElse(null);
    }

    /**
     * Finds the most qualified elemental requirement to refresh from the learning requirement.
     */
    private ElementalRequirement findQualifiedElementalRequirement(List<LearningResult> pastPerformance, LearningRequirement learningRequirementToRefresh) {
        return learningRequirementToRefresh.calculateQualifiedElementalRequirements(
                        pastPerformance.get(0).getStudent()
                                .getLearningHistoryByKnowledgeSubject(learningResultPersistence, learningRequirementToRefresh.getKnowledgeSubjectId()))
                .stream()
                .max(Comparator.comparingInt(ElementalRequirement::getOrdinal))
                .orElse(null);
    }

    public static class RefreshRule extends PersonalizationRule<ElementalRequirement> {
        public RefreshRule(ElementalRequirement context) {
            super(context);
        }
    }
}

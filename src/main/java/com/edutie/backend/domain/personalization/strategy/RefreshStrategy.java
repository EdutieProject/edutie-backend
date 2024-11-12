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
 * Personalization strategy for refreshing the knowledge of given elemental req
 */
@Component
@RequiredArgsConstructor
public class RefreshStrategy implements PersonalizationStrategy<ElementalRequirement, RefreshStrategy.RefreshRule> {
    private final KnowledgeMapService knowledgeMapService;
    private final LearningResultPersistence learningResultPersistence;
    private final int REQUIRED_STREAK_SIZE = 3;

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param learningRequirements learning requirements to consider
     * @param pastPerformance      learning history to consider when deciding qualification
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<RefreshRule> qualifyRule(Set<LearningRequirement> learningRequirements, List<LearningResult> pastPerformance) {
        if (pastPerformance.isEmpty())
            return Optional.empty();
        pastPerformance.sort(Comparator.comparing(AuditableEntityBase::getCreatedOn));
        // get the learning streaks happening in the past
        final Set<LearningRequirementId> learningRequirementIds = new HashSet<>();
        List<List<LearningResult>> learningStreaks = new ArrayList<>();
        List<LearningResult> currentStreak = new ArrayList<>();
        for (int i = 1; i < learningRequirements.size(); i++) {
            learningRequirementIds.clear();
            learningRequirementIds.addAll(pastPerformance.get(i - 1).getLearningRequirementIds());
            LearningResult learningResult = pastPerformance.get(i);
            if (learningResult.getLearningRequirementIds().stream().anyMatch(learningRequirementIds::contains)) {
                currentStreak.add(learningResult);
                if (currentStreak.size() >= REQUIRED_STREAK_SIZE) {
                    learningStreaks.add(currentStreak);
                    currentStreak.clear();
                }
            } else {
                currentStreak.clear();
            }
            if (learningStreaks.size() >= 3) {
                break;
            }
        }
        if (learningStreaks.isEmpty())
            return Optional.empty();
        Set<LearningRequirement> learningRequirementsToRefresh = learningStreaks.stream().flatMap(Collection::stream)
                .flatMap(o -> o.getAssociatedLearningRequirements().stream()).collect(Collectors.toSet());
        // measure the correlations of requirements to be refreshed
        LearningRequirementCorrelation mostCorrelatedCorrelation = knowledgeMapService
                .getLearningRequirementCorrelations(learningRequirements, learningRequirementsToRefresh).getValue()
                .stream().max(Comparator.comparing(LearningRequirementCorrelation::getCorrelationFactor)).get();
        LearningRequirement learningRequirementToRefresh = learningRequirementsToRefresh.stream()
                .filter(o -> o.getId().equals(mostCorrelatedCorrelation.getCorrelatedLearningRequirementId()))
                .toList().getFirst();

        ElementalRequirement elementalRequirementToRefresh = learningRequirementToRefresh.calculateQualifiedElementalRequirements(
                pastPerformance.get(0).getStudent().getLearningHistoryByKnowledgeSubject(learningResultPersistence, learningRequirementToRefresh.getKnowledgeSubjectId())
        ).stream().max(Comparator.comparingInt(ElementalRequirement::getOrdinal)).get();

        return Optional.of(new RefreshRule(elementalRequirementToRefresh));
    }

    public static class RefreshRule extends PersonalizationRule<ElementalRequirement> {
        public RefreshRule(ElementalRequirement context) {
            super(context);
        }
    }
}

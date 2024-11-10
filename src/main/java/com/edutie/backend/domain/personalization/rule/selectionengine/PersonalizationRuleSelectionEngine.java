package com.edutie.backend.domain.personalization.rule.selectionengine;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.rule.RecommendationPersonalizationStrategy;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PersonalizationRuleSelectionEngine {
    private final RecommendationPersonalizationStrategy recommendationPersonalizationStrategy;


    private List<PersonalizationStrategy<?, ? extends PersonalizationRule<?>>> getPersonalizationStrategies() {
        return List.of(
                recommendationPersonalizationStrategy
        );
    }

    public Set<PersonalizationRule<?>> chooseRulesByRequirementsAndHistory(
            Set<LearningRequirement> learningRequirements,
            List<LearningResult> pastResults
    ) {
        Set<PersonalizationRule<?>> rules = new HashSet<>();
        for (PersonalizationStrategy<?, ? extends PersonalizationRule<?>> strategy : getPersonalizationStrategies()) {
            strategy.qualifyRule(learningRequirements, pastResults).ifPresent(rules::add);
        }
        return rules.stream().limit(2L).collect(Collectors.toSet());
    }
}

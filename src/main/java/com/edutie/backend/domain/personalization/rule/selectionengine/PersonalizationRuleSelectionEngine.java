package com.edutie.backend.domain.personalization.rule.selectionengine;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.rule.base.PersonalizationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class PersonalizationRuleSelectionEngine {
    private final List<PersonalizationStrategy<?, ? extends PersonalizationRule<?>>> personalizationStrategies;


    public Set<PersonalizationRule<?>> chooseRulesByRequirementsAndHistory(
            Set<LearningRequirement> learningRequirements,
            List<LearningResult> pastResults
    ) {
        Set<PersonalizationRule<?>> rules = new HashSet<>();

        for (PersonalizationStrategy<?, ? extends PersonalizationRule<?>> strategy : personalizationStrategies.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getPriority(), s1.getPriority())) // Higher priorities first
                .toList()) {
            strategy.qualifyRule(learningRequirements, pastResults).ifPresent(rules::add);
            if (rules.size() == 2)
                break;
        }
        return rules;
    }
}

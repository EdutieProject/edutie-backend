package com.edutie.backend.domain.personalization.strategy.selectionengine;

import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationRule;
import com.edutie.backend.domain.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.backend.domain.personalization.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The component that uses registered personalization strategies to choose
 * personalization rules.
 * @see PersonalizationStrategy Personalization Strategy
 */
@RequiredArgsConstructor
@Component
public class PersonalizationRuleSelectionEngine {
    private final List<PersonalizationStrategy<?, ? extends PersonalizationRule<?>>> personalizationStrategies;


    public Set<PersonalizationRule<?>> chooseRules(
            Student student, Set<LearningRequirement> learningRequirements
    ) {
        Set<PersonalizationRule<?>> rules = new HashSet<>();

        for (PersonalizationStrategy<?, ? extends PersonalizationRule<?>> strategy : personalizationStrategies.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getPriority(), s1.getPriority())) // Higher priorities first
                .toList()) {
            strategy.qualifyRule(student, learningRequirements).ifPresent(rules::add);
            if (rules.size() == 2)
                break;
        }
        return rules;
    }
}

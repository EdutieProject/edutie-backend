package com.edutie.domain.core.personalization.strategy.selectionengine;

import com.edutie.domain.core.education.learningrequirement.LearningSubject;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationStrategy;
import com.edutie.domain.core.learning.student.Student;
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
            Student student, Set<LearningSubject> learningSubjects
    ) {
        Set<PersonalizationRule<?>> rules = new HashSet<>();

        for (PersonalizationStrategy<?, ? extends PersonalizationRule<?>> strategy : personalizationStrategies.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getPriority(), s1.getPriority())) // Higher priorities first
                .toList()) {
            strategy.qualifyRule(student, learningSubjects).ifPresent(rules::add);
            if (rules.size() == 2)
                break;
        }
        return rules;
    }
}

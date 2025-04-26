package com.edutie.domain.core.personalization.engine;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.common.PersonalizationRule;
import com.edutie.domain.core.personalization.common.PersonalizationStrategy;
import com.edutie.domain.core.personalization.learningexperience.contentadjustment.base.ContentAdjustmentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The component that uses registered personalization strategies to choose
 * personalization rules.
 *
 * @see PersonalizationStrategy Personalization Strategy
 */
@RequiredArgsConstructor
@Component
public class ContentAdjustmentPersonalizationEngine {
    protected final List<ContentAdjustmentStrategy<?, ? extends PersonalizationRule<?>>> personalizationStrategies;

    protected int getMaxRuleAmount() {
        return 2;
    }

    public Set<PersonalizationRule<?>> chooseRules(
            Student student, Set<LearningSubject> learningSubjects
    ) {
        Set<PersonalizationRule<?>> rules = new HashSet<>();

        for (PersonalizationStrategy<?, ? extends PersonalizationRule<?>> strategy : personalizationStrategies.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getPriority(), s1.getPriority())) // Higher priorities first
                .toList()) {
            strategy.qualifyRule(student, learningSubjects).ifPresent(rules::add);
            if (rules.size() == getMaxRuleAmount())
                break;
        }
        return rules;
    }
}

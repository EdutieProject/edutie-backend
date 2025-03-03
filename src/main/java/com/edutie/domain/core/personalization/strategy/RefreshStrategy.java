package com.edutie.domain.core.personalization.strategy;

import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationRule;
import com.edutie.domain.core.personalization.strategy.base.PersonalizationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * Personalization strategy for refreshing the knowledge of given elemental requirements.
 */
@Component
@RequiredArgsConstructor
public class RefreshStrategy implements PersonalizationStrategy<ElementalRequirement, RefreshStrategy.RefreshRule> {

    @Override
    public Optional<RefreshRule> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {

        return Optional.empty();
    }

    public static class RefreshRule extends PersonalizationRule<ElementalRequirement> {
        public RefreshRule(ElementalRequirement context) {
            super(context);
        }
    }
}

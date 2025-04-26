package com.edutie.domain.core.personalization.learningexperience.contentadjustment;

import com.edutie.domain.core.education.elementalrequirement.ElementalRequirement;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.common.PersonalizationRuleBase;
import com.edutie.domain.core.personalization.learningexperience.contentadjustment.base.ContentAdjustmentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * Personalization strategy for refreshing the knowledge of given elemental requirements.
 */
@Component
@RequiredArgsConstructor
public class RefreshStrategy implements ContentAdjustmentStrategy<ElementalRequirement, RefreshStrategy.RefreshRuleBase> {

    @Override
    public Optional<RefreshRuleBase> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {

        return Optional.empty();
    }

    public static class RefreshRuleBase extends PersonalizationRuleBase<ElementalRequirement> {
        public RefreshRuleBase(ElementalRequirement context) {
            super(context);
        }
    }
}

package com.edutie.domain.core.personalization.engine;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.common.PersonalizationRule;
import com.edutie.domain.core.personalization.common.PersonalizationStrategy;
import com.edutie.domain.core.personalization.learningexperience.activitytypechoice.base.LearningExperienceActivityTypeChoiceStrategy;
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
public class ActivityTypeChoicePersonalizationEngine {
    protected final List<LearningExperienceActivityTypeChoiceStrategy<?, ?>> personalizationStrategies;

    protected int getMaxRuleAmount() {
        return 1;
    }

    public PersonalizationRule<? extends Class<? extends Activity>> chooseRule(
            Student student, Set<LearningSubject> learningSubjects
    ) {
        Set<PersonalizationRule<? extends Class<? extends Activity>>> rules = new HashSet<>();

        for (LearningExperienceActivityTypeChoiceStrategy<?, ?> strategy : personalizationStrategies.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getPriority(), s1.getPriority())) // Higher priorities first
                .toList()) {
            strategy.qualifyRule(student, learningSubjects).ifPresent(rules::add);
            if (rules.size() == getMaxRuleAmount())
                break;
        }
        return rules.stream().findFirst().get();
    }
}

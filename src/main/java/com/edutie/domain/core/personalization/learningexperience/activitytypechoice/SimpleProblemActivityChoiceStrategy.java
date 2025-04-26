package com.edutie.domain.core.personalization.learningexperience.activitytypechoice;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.learningexperience.entities.activity.SimpleProblemActivity;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.common.PersonalizationRuleBase;
import com.edutie.domain.core.personalization.learningexperience.activitytypechoice.base.LearningExperienceActivityTypeChoiceStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * Remediation personalization strategy for remediating the content based on the knowledge of
 * a strongly correlated subject that is well understood.
 */
@Component
@RequiredArgsConstructor
public class SimpleProblemActivityChoiceStrategy
        implements LearningExperienceActivityTypeChoiceStrategy<Class<SimpleProblemActivity>, SimpleProblemActivityChoiceStrategy.Rule> {

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     *
     * @param student          student
     * @param learningSubjects learning requirements to consider
     * @return Optional Personalization Rule
     */
    @Override
    public Optional<Rule> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
        return Optional.of(new Rule(SimpleProblemActivity.class));
    }

    public static class Rule extends PersonalizationRuleBase<Class<SimpleProblemActivity>> {

        public Rule(Class<SimpleProblemActivity> context) {
            super(context);
        }
    }
}

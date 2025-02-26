package com.edutie.domain.core.personalization.strategy.base;

import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.student.Student;

import java.util.Optional;
import java.util.Set;

/**
 * Personalization strategy is the provider for personalization rules which are later
 * used by the personalization technology to personalize the learning resource.
 *
 * @param <TPersonalizationContext>
 * @param <TRule>
 */
public interface PersonalizationStrategy<TPersonalizationContext, TRule extends PersonalizationRule<TPersonalizationContext>> {

    /**
     * Function qualifying the rule of the personalization strategy. When the personalization strategy
     * does not apply, the returned optional is empty.
     * The strategy should use dependency injection to get the appropriate learning results using the
     * student profile only.
     *
     * @param student              student for which we want to qualify the rule
     * @param learningSubjects learning requirements to consider
     * @return Optional Personalization Rule - depending on whether the rule is qualified.
     */
    Optional<TRule> qualifyRule(Student student, Set<LearningSubject> learningSubjects);

    /**
     * Returns the priority level of this strategy.
     * Higher values for higher priority, lower for diminishing the priority.
     */
    default int getPriority() {
        return 0;
    }

}

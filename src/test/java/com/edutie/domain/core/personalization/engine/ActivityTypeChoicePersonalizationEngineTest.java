package com.edutie.domain.core.personalization.engine;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.common.LearningObjectiveType;
import com.edutie.domain.core.learning.learningexperience.entities.activity.base.Activity;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.common.PersonalizationRule;
import com.edutie.domain.core.personalization.learningexperience.activitytypechoice.base.LearningExperienceActivityTypeChoiceStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

class MockActivity implements Activity {

    @Override
    public LearningObjectiveType getActivityType() {
        return LearningObjectiveType.ANALYZE;
    }
}

class AnotherMockActivity implements Activity {

    @Override
    public LearningObjectiveType getActivityType() {
        return LearningObjectiveType.ANALYZE;
    }
}

@SpringBootTest
public class ActivityTypeChoicePersonalizationEngineTest {

    private static final List<LearningExperienceActivityTypeChoiceStrategy<?, ?>> strategies = List.of(
            new LearningExperienceActivityTypeChoiceStrategy<Class<MockActivity>, PersonalizationRule<Class<MockActivity>>>() {
                @Override
                public Optional<PersonalizationRule<Class<MockActivity>>> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
                    return Optional.empty();
                }
            },
            new LearningExperienceActivityTypeChoiceStrategy<Class<MockActivity>, PersonalizationRule<Class<MockActivity>>>() {
                @Override
                public Optional<PersonalizationRule<Class<MockActivity>>> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
                    return Optional.of(() -> MockActivity.class);
                }

                @Override
                public int getPriority() {
                    return 2;
                }
            },
            new LearningExperienceActivityTypeChoiceStrategy<Class<AnotherMockActivity>, PersonalizationRule<Class<AnotherMockActivity>>>() {
                @Override
                public Optional<PersonalizationRule<Class<AnotherMockActivity>>> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
                    return Optional.of(() -> AnotherMockActivity.class);
                }
            }
    );

    @Test
    void chooseRule() {
        ActivityTypeChoicePersonalizationEngine engine = new ActivityTypeChoicePersonalizationEngine(strategies);
        PersonalizationRule<?> selectedRule = engine.chooseRule(Student.create(new UserId()), Set.of());

        Assertions.assertEquals(MockActivity.class, selectedRule.getPersonalizationContext());
    }
}
package com.edutie.domain.core.personalization.engine;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.personalization.common.PersonalizationRule;
import com.edutie.domain.core.personalization.learningexperience.contentadjustment.base.ContentAdjustmentStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@SpringBootTest
class ContentAdjustmentPersonalizationEngineTest {

    private static final List<ContentAdjustmentStrategy<?, ?>> strategies = List.of(
            new ContentAdjustmentStrategy<Feedback, PersonalizationRule<Feedback>>() {
                @Override
                public Optional<PersonalizationRule<Feedback>> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
                    return Optional.of(() -> Feedback.of("Hello"));
                }

                @Override
                public int getPriority() {
                    return 1;
                }
            },
            new ContentAdjustmentStrategy<Feedback, PersonalizationRule<Feedback>>() {
                @Override
                public Optional<PersonalizationRule<Feedback>> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
                    return Optional.of(() -> Feedback.of("World"));
                }

                @Override
                public int getPriority() {
                    return 2;
                }
            },
            new ContentAdjustmentStrategy<Feedback, PersonalizationRule<Feedback>>() {
                @Override
                public Optional<PersonalizationRule<Feedback>> qualifyRule(Student student, Set<LearningSubject> learningSubjects) {
                    return Optional.of(() -> Feedback.of("Universe"));
                }

                @Override
                public int getPriority() {
                    return 3;
                }
            }
    );

    @Test
    void chooseRules() {
        ContentAdjustmentPersonalizationEngine engine = new ContentAdjustmentPersonalizationEngine(strategies);

        Set<PersonalizationRule<?>> selectedRules = engine.chooseRules(Student.create(new UserId()), Set.of());

        Assertions.assertEquals(2, selectedRules.size());
        Assertions.assertTrue(selectedRules.stream().anyMatch(o -> o.getPersonalizationContext().equals(Feedback.of("Universe"))));
        Assertions.assertTrue(selectedRules.stream().anyMatch(o -> o.getPersonalizationContext().equals(Feedback.of("World"))));
    }
}

package com.edutie.domain.personalization;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.education.learningsubject.LearningSubject;
import com.edutie.domain.core.education.learningsubject.identities.LearningSubjectId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.domain.core.learning.learningresult.LearningResult;
import com.edutie.domain.core.learning.learningresult.entities.Assessment;
import com.edutie.domain.core.learning.learningresult.valueobjects.Feedback;
import com.edutie.domain.core.learning.learningresult.entities.submission.common.SolutionSubmissionBase;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.mocks.EducationMocks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
public class LearningResultTests {
    private final UserId userId = new UserId();
    private final Educator educator = Educator.create(userId, Administrator.create(userId));
    private final Student student = Student.create(userId);

    @Test
    public void isSuccessfulFalseTest() {
        LearningResult learningResult = LearningResult.create(
                SolutionSubmissionBase.create(student, null, DefinitionType.DYNAMIC, "Report text", 0),
                new Feedback("That is a feedback"),
                Set.of(
                        Assessment.create(new LearningSubjectId(), Grade.MIN_GRADE, Feedback.of("Text of the feedback"), List.of()),
                        Assessment.create(new LearningSubjectId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback part 2"), List.of())
                )
        );

        Assertions.assertFalse(learningResult.isSuccessful());
    }

    @Test
    public void isSuccessfulTrueTest() {
        LearningResult learningResult = LearningResult.create(
                SolutionSubmissionBase.create(student, null, DefinitionType.DYNAMIC, "Report text", 0),
                new Feedback("That is a feedback"),
                Set.of(
                        Assessment.create(new LearningSubjectId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback"), List.of()),
                        Assessment.create(new LearningSubjectId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback part 2"), List.of())
                )
        );

        Assertions.assertTrue(learningResult.isSuccessful());
    }

    @Test
    public void difficultyFactorTest() {
        LearningSubject learningSubject = EducationMocks.independentLearningRequirement(educator);
        Assessment assessment = Assessment.create(new LearningSubjectId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback"),
                // get last elemental requirement only from 3 out there
                learningSubject.getRequirements().subList(2,3));

        System.out.println(assessment.getDifficultyFactor());
        Assertions.assertEquals(1D, assessment.getDifficultyFactor());

        LearningSubject learningSubject2 = EducationMocks.independentLearningRequirement(educator);
        Assessment assessment2 = Assessment.create(new LearningSubjectId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback"),
                // get last elemental requirement only from 3 out there
                learningSubject2.getRequirements().subList(1,2));

        System.out.println(assessment2.getDifficultyFactor());
        Assertions.assertEquals(0.67D, assessment2.getDifficultyFactor());
    }
}

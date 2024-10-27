package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.enums.FeedbackType;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.mocks.LearningResourceMocks;
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
                student,
                SolutionSubmission.create(student, null, "Report text", 0),
                new Feedback("That is a feedback", FeedbackType.NEUTRAL),
                Set.of(
                        Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, "Text of the feedback", List.of()),
                        Assessment.create(new LearningRequirementId(), Grade.MAX_GRADE, "Text of the feedback part 2", List.of())
                )
        );

        Assertions.assertFalse(learningResult.isSuccessful());
    }

    @Test
    public void isSuccessfulTrueTest() {
        LearningResult learningResult = LearningResult.create(
                student,
                SolutionSubmission.create(student, null, "Report text", 0),
                new Feedback("That is a feedback", FeedbackType.NEUTRAL),
                Set.of(
                        Assessment.create(new LearningRequirementId(), Grade.MAX_GRADE, "Text of the feedback", List.of()),
                        Assessment.create(new LearningRequirementId(), Grade.MAX_GRADE, "Text of the feedback part 2", List.of())
                )
        );

        Assertions.assertTrue(learningResult.isSuccessful());
    }
}

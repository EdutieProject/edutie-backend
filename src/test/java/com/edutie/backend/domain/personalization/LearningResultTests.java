package com.edutie.backend.domain.personalization;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.mocks.EducationMocks;
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
                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "Report text", 0),
                new Feedback("That is a feedback"),
                Set.of(
                        Assessment.create(new LearningRequirementId(), Grade.MIN_GRADE, Feedback.of("Text of the feedback"), List.of()),
                        Assessment.create(new LearningRequirementId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback part 2"), List.of())
                )
        );

        Assertions.assertFalse(learningResult.isSuccessful());
    }

    @Test
    public void isSuccessfulTrueTest() {
        LearningResult learningResult = LearningResult.create(
                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "Report text", 0),
                new Feedback("That is a feedback"),
                Set.of(
                        Assessment.create(new LearningRequirementId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback"), List.of()),
                        Assessment.create(new LearningRequirementId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback part 2"), List.of())
                )
        );

        Assertions.assertTrue(learningResult.isSuccessful());
    }

    @Test
    public void difficultyFactorTest() {
        LearningRequirement learningRequirement = EducationMocks.independentLearningRequirement(educator);
        Assessment assessment = Assessment.create(new LearningRequirementId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback"),
                // get last elemental requirement only from 3 out there
                learningRequirement.getElementalRequirements().subList(2,3));

        System.out.println(assessment.getDifficultyFactor());
        Assertions.assertEquals(1D, assessment.getDifficultyFactor());

        LearningRequirement learningRequirement2 = EducationMocks.independentLearningRequirement(educator);
        Assessment assessment2 = Assessment.create(new LearningRequirementId(), Grade.MAX_GRADE, Feedback.of("Text of the feedback"),
                // get last elemental requirement only from 3 out there
                learningRequirement2.getElementalRequirements().subList(1,2));

        System.out.println(assessment2.getDifficultyFactor());
        Assertions.assertEquals(0.67D, assessment2.getDifficultyFactor());
    }
}

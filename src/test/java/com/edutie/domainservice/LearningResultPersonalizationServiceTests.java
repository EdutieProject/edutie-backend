package com.edutie.domainservice;

import com.edutie.domain.core.administration.UserId;
import com.edutie.domain.core.administration.administrator.Administrator;
import com.edutie.domain.core.education.educator.Educator;
import com.edutie.domain.core.learning.learningexperience.LearningExperience;
import com.edutie.domain.core.learning.learningexperience.identities.LearningExperienceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.domain.core.learning.learningresult.entities.submission.common.SolutionSubmissionBase;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.service.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.mocks.LearningResourceMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LearningResultPersonalizationServiceTests {
    private final UserId userId = new UserId();
    private final Student student = Student.create(userId);
    private final Educator educator = Educator.create(userId, Administrator.create(userId));


    @Test
    public void personalizationSchemaTest() {
        LearningExperience learningExperience = LearningResourceMocks.sampleLearningResource(student, educator);
        SolutionSubmissionBase solutionSubmissionBase = SolutionSubmissionBase.create(
                student,
                new LearningExperienceId(),
                DefinitionType.DYNAMIC,
                "Solution report text",
                0);

        AssessmentSchema assessmentSchema = AssessmentSchema.create(learningExperience, solutionSubmissionBase);

        assertEquals(solutionSubmissionBase.getStudent(), assessmentSchema.getStudent());
    }

    @Test
    public void personalizationServiceTest() {
        //TODO
    }

}

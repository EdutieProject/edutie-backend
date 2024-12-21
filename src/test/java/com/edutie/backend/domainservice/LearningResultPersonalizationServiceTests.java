package com.edutie.backend.domainservice;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.personalization.learningresource.LearningResource;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.backend.mocks.LearningResourceMocks;
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
        LearningResource learningResource = LearningResourceMocks.sampleLearningResource(student, educator);
        SolutionSubmission solutionSubmission = SolutionSubmission.create(
                student,
                new LearningResourceId(),
                DefinitionType.DYNAMIC,
                "Solution report text",
                0);

        AssessmentSchema assessmentSchema = AssessmentSchema.create(learningResource, solutionSubmission);

        assertEquals(solutionSubmission.getStudent(), assessmentSchema.getStudent());
    }

    @Test
    public void personalizationServiceTest() {
        //TODO
    }

}

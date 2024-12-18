package com.edutie.backend.domainservice;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domainservice.personalization.learningresult.LearningResultPersonalizationService;
import com.edutie.backend.domainservice.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.LearningResultRepository;
import com.edutie.backend.mocks.LearningHistoryMocker;
import com.edutie.backend.mocks.LearningResourceMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LearningResultPersonalizationServiceTests {
    private final UserId userId = new UserId();
    private final Student student = Student.create(userId);
    private final Educator educator = Educator.create(userId, Administrator.create(userId));

    @MockBean
    private LearningResultPersonalizationService learningResultPersonalizationService;

    @Mock
    LearningResultRepository learningResultRepository;

    private final LearningResultPersistence learningResultPersistence = LearningHistoryMocker.baseLearningHistoryMock();

    @Test
    public void personalizationSchemaTest() {
        SolutionSubmission solutionSubmission = SolutionSubmission.create(
                student,
                LearningResourceMocks.sampleLearningResource(student, educator),
                "Solution report text",
                0);

        AssessmentSchema assessmentSchema = AssessmentSchema.create(solutionSubmission);

        assertEquals(solutionSubmission.getStudent(), assessmentSchema.getStudent());
        assertEquals(solutionSubmission.getLearningResource().getDefinitionId(), assessmentSchema.getLearningResourceDefinitionId());
    }

    @Test
    public void personalizationServiceTest() {
        //TODO
    }

}

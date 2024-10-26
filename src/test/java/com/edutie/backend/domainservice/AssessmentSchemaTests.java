package com.edutie.backend.domainservice;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.administration.administrator.Administrator;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.domainservice.personalization.learningresult.schema.AssessmentSchema;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.LearningResultRepository;
import com.edutie.backend.mocks.LearningResourceMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import validation.WrapperResult;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AssessmentSchemaTests {
    private final UserId userId = new UserId();
    private final Student student = Student.create(userId);
    private final Educator educator = Educator.create(userId, Administrator.create(userId));

    @Mock
    LearningResultRepository learningResultRepository;

    private final LearningResultPersistence learningResultPersistence = new LearningResultPersistence() {
        @Override
        public WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxDate) {
            return WrapperResult.successWrapper(List.of());
        }

        @Override
        public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByLearningResourceDefinitionId(StudentId studentId, LearningResourceDefinitionId learningResourceDefinitionId) {
            return WrapperResult.successWrapper(List.of());
        }

        @Override
        public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByKnowledgeSubjectId(StudentId studentId, KnowledgeSubjectId knowledgeSubjectId) {
            return WrapperResult.successWrapper(List.of());
        }

        @Override
        public JpaRepository<LearningResult, LearningResultId> getRepository() {
            return learningResultRepository;
        }

        @Override
        public Class<LearningResult> entityClass() {
            return LearningResult.class;
        }
    };

    @Test
    public void createAssessmentSchemaTest() {
        SolutionSubmission solutionSubmission = SolutionSubmission.create(
                student,
                LearningResourceMocks.sampleLearningResource(student, learningResultPersistence, educator),
                "Solution report text",
                0);

        AssessmentSchema assessmentSchema = AssessmentSchema.create(solutionSubmission);

        assertEquals(solutionSubmission.getStudent(), assessmentSchema.getStudent());
        assertEquals(solutionSubmission.getLearningResource().getDefinitionId(), assessmentSchema.getLearningResourceDefinitionId());
    }
}

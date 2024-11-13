package com.edutie.backend.mocks;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.entities.Assessment;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Feedback;
import com.edutie.backend.domain.personalization.learningresult.valueobjects.Grade;
import com.edutie.backend.domain.personalization.solutionsubmission.SolutionSubmission;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import validation.Error;
import validation.WrapperResult;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class LearningHistoryMocker {
    public static LearningResult createLearningResultWithCreatedOnInThePast(
            SolutionSubmission solutionSubmission,
            Feedback feedback,
            Set<Assessment> assessments,
            LocalDateTime createdOnDate
    ) throws Throwable {

        // Create learning result using the recommended constructor method
        LearningResult learningResult = LearningResult.create(solutionSubmission, feedback, assessments);

        // Use reflection to set the createdOn field to a past date
        Field createdOnField = AuditableEntityBase.class.getDeclaredField("createdOn");
        createdOnField.setAccessible(true); // Allow access to private field
        createdOnField.set(learningResult, createdOnDate); // Set the desired past date

        return learningResult;
    }

    public static LearningResultPersistence learningResultPersistenceForFeedbackRemediationStrategy(Student student, LearningRequirement learningRequirement, Grade grade) {
        return new LearningResultPersistence() {
            @Override
            public WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxPastDate){
                try {
                    return WrapperResult.successWrapper(List.of(
                            createLearningResultWithCreatedOnInThePast(
                                    SolutionSubmission.create(student, null, "", 0),
                                    Feedback.of("Hello"),
                                    Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), List.of())),
                                    LocalDateTime.now().minusDays(1)
                            ),
                            createLearningResultWithCreatedOnInThePast(
                                    SolutionSubmission.create(student, null, "", 0),
                                    Feedback.of("World"),
                                    Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), List.of())),
                                    LocalDateTime.now().minusDays(2)
                            )));
                } catch (Throwable throwable) {
                    return WrapperResult.failureWrapper(new Error("??? no code", ""));
                }
            }

            @Override
            public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByLearningResourceDefinitionId(StudentId studentId, LearningResourceDefinitionId learningResourceDefinitionId) {
                return null;
            }

            @Override
            public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByKnowledgeSubjectId(StudentId studentId, KnowledgeSubjectId knowledgeSubjectId) {
                return null;
            }

            @Override
            public JpaRepository<LearningResult, LearningResultId> getRepository() {
                return null;
            }

            @Override
            public Class<LearningResult> entityClass() {
                return null;
            }
        };
    }
}

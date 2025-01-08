package com.edutie.backend.mocks;

import com.edutie.backend.domain.common.base.AuditableEntityBase;
import com.edutie.backend.domain.common.generationprompt.PromptFragment;
import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.education.learningrequirement.entities.ElementalRequirement;
import com.edutie.backend.domain.education.learningrequirement.identities.LearningRequirementId;
import com.edutie.backend.domain.personalization.learningresource.identities.LearningResourceId;
import com.edutie.backend.domain.personalization.learningresourcedefinition.enums.DefinitionType;
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
    ) {
        try {
            // Create learning result using the recommended constructor method
            LearningResult learningResult = LearningResult.create(solutionSubmission, feedback, assessments);

            // Use reflection to set the createdOn field to a past date
            Field createdOnField = AuditableEntityBase.class.getDeclaredField("createdOn");
            createdOnField.setAccessible(true); // Allow access to private field
            createdOnField.set(learningResult, createdOnDate); // Set the desired past date

            return learningResult;
        } catch (Throwable thr) {
            throw new RuntimeException(thr);
        }

    }

    public static LearningResultPersistence learningResultPersistenceForFeedbackRemediationStrategy(Student student, LearningRequirement learningRequirement, Grade grade) {
        return new MockLearningResultPersistence() {
            @Override
            public WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxPastDate) {
                return WrapperResult.successWrapper(List.of(
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, new LearningResourceId(), DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("Hello"),
                                Set.of(
                                        Assessment.create(learningRequirement.getId(), grade, Feedback.of("You need to improve"), List.of()),
                                        Assessment.create(learningRequirement.getId(), Grade.of(grade.gradeNumber() + 1), Feedback.of("Its better here"), List.of())
                                ),
                                LocalDateTime.now().minusDays(1)
                        ),
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, new LearningResourceId(), DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("World"),
                                Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of("You need to improve"), List.of())),
                                LocalDateTime.now().minusDays(2)
                        )
                ));
            }
        };
    }

    public static LearningResultPersistence learningResultPersistenceForRecommendationStrategy(Student student, LearningRequirement learningRequirement, Grade grade) {
        return new MockLearningResultPersistence() {
            @Override
            public WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxPastDate) {
                return WrapperResult.successWrapper(List.of(
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("Hello"),
                                Set.of(Assessment.create(learningRequirement.getId(), Grade.of(1), Feedback.of(""), List.of())),
                                LocalDateTime.now().minusDays(1)
                        ),
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("World"),
                                Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""),
                                        List.of(ElementalRequirement.create(learningRequirement, PromptFragment.empty(), PromptFragment.empty(), 1)))),
                                LocalDateTime.now().minusDays(2)
                        )
                ));
            }
        };
    }

    public static LearningResultPersistence learningResultPersistenceForFamiliarRemediationStrategy(Student student, LearningRequirement learningRequirement, Grade grade) {
        return new MockLearningResultPersistence() {
            @Override
            public WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxPastDate) {
                return WrapperResult.successWrapper(List.of(
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, new LearningResourceId(), DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("Hello"),
                                Set.of(Assessment.create(learningRequirement.getId(), Grade.of(1), Feedback.of(""), learningRequirement.getElementalRequirements().subList(0, 1))),
                                LocalDateTime.now().minusDays(1)
                        ),
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, new LearningResourceId(), DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("Universe"),
                                Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), learningRequirement.getElementalRequirements().subList(1, 2))),
                                LocalDateTime.now().minusDays(1)
                        ),
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, new LearningResourceId(), DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("World"),
                                Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), learningRequirement.getElementalRequirements().subList(0, 2))),
                                LocalDateTime.now().minusDays(2)
                        )
                ));
            }
        };
    }

    public static LearningResultPersistence learningResultPersistenceForRefreshStrategy(Student student, LearningRequirement learningRequirement, Grade grade) {
        return new MockLearningResultPersistence() {
            @Override
            public WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxPastDate) {
                return WrapperResult.successWrapper(List.of(
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("Hello"),
                                Set.of(Assessment.create(new LearningRequirementId(), grade, Feedback.of(""), List.of())),
                                LocalDateTime.now().minusDays(1)
                        ),
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("Hello"),
                                Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), List.of(ElementalRequirement.create(learningRequirement, PromptFragment.empty(), PromptFragment.empty(), 1)))),
                                LocalDateTime.now().minusDays(2).minusMinutes(1)
                        ),
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("Hello"),
                                Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), List.of(ElementalRequirement.create(learningRequirement, PromptFragment.empty(), PromptFragment.empty(), 1)))),
                                LocalDateTime.now().minusDays(2).minusMinutes(2)
                        ),
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("Hello"),
                                Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), List.of(ElementalRequirement.create(learningRequirement, PromptFragment.empty(), PromptFragment.empty(), 1)))),
                                LocalDateTime.now().minusDays(2).minusMinutes(3)
                        ),
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("Hello"),
                                Set.of(Assessment.create(new LearningRequirementId(), grade, Feedback.of(""), List.of())),
                                LocalDateTime.now().minusDays(2).minusMinutes(5)
                        ),
                        createLearningResultWithCreatedOnInThePast(
                                SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                Feedback.of("World"),
                                Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), List.of())),
                                LocalDateTime.now().minusDays(3)
                        )
                ));
            }

            /**
             * Provides learning results associated with the L. requirement of certain knowledge subject id created by given student.
             *
             * @param studentId
             * @param knowledgeSubjectId knowledge subject id
             * @return Learning Result List Wrapper Result
             */
            @Override
            public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByKnowledgeSubjectId(StudentId studentId, KnowledgeSubjectId knowledgeSubjectId) {
                return WrapperResult.successWrapper(
                        List.of(
                                createLearningResultWithCreatedOnInThePast(
                                        SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                        Feedback.of("Hello"),
                                        Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), List.of(ElementalRequirement.create(learningRequirement, PromptFragment.empty(), PromptFragment.empty(), 1)))),
                                        LocalDateTime.now().minusDays(2).minusMinutes(1)
                                ),
                                createLearningResultWithCreatedOnInThePast(
                                        SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                        Feedback.of("Hello"),
                                        Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), List.of(ElementalRequirement.create(learningRequirement, PromptFragment.empty(), PromptFragment.empty(), 1)))),
                                        LocalDateTime.now().minusDays(2).minusMinutes(2)
                                ),
                                createLearningResultWithCreatedOnInThePast(
                                        SolutionSubmission.create(student, null, DefinitionType.DYNAMIC, "", 0),
                                        Feedback.of("Hello"),
                                        Set.of(Assessment.create(learningRequirement.getId(), grade, Feedback.of(""), List.of(ElementalRequirement.create(learningRequirement, PromptFragment.empty(), PromptFragment.empty(), 1)))),
                                        LocalDateTime.now().minusDays(2).minusMinutes(3)
                                )
                        ));
            }
        };
    }
}

class MockLearningResultPersistence implements LearningResultPersistence {

    private final static Error notImplementedError = new Error("NOT-IMPLEMENTED-MOCK", "Not implemented mocked functionality");

    /**
     * Retrieves latest results associated with given student. Results are ordered from the latest to the older.
     * The retrieved amount is supplied as a result
     *
     * @param studentId   student id
     * @param amount      learning result amount
     * @param maxPastDate
     * @return Wrapper Result of Learning Results
     */
    @Override
    public WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxPastDate) {
        return WrapperResult.successWrapper(List.of());
    }

    /**
     * Retrieves latest learning result of student, if any.
     *
     * @param studentId student id
     * @return Wrapper Result of Learning Result
     */
    @Override
    public WrapperResult<LearningResult> getSingleLatestResultOfStudent(StudentId studentId) {
        return WrapperResult.failureWrapper(notImplementedError);
    }

    /**
     * Provides learning results associated with given learning resource definition ids.
     *
     * @param studentId                     student id
     * @param learningResourceDefinitionIds learning resource definition ids set
     * @return Wrapper result of Learning Result list
     */
    @Override
    public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByLearningResourceDefinitionIds(StudentId studentId, Set<LearningResourceDefinitionId> learningResourceDefinitionIds) {
        return WrapperResult.successWrapper(List.of());
    }

    /**
     * Provides learning results associated with certain learning resource definition id created by given student.
     *
     * @param studentId
     * @param learningResourceDefinitionId learning resource definition id
     * @return Learning Result List Wrapper Result
     */
    @Override
    public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByLearningResourceDefinitionId(StudentId studentId, LearningResourceDefinitionId learningResourceDefinitionId) {
        return WrapperResult.successWrapper(List.of());
    }

    /**
     * Provides learning results associated with the L. requirement of certain knowledge subject id created by given student.
     *
     * @param studentId
     * @param knowledgeSubjectId knowledge subject id
     * @return Learning Result List Wrapper Result
     */
    @Override
    public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByKnowledgeSubjectId(StudentId studentId, KnowledgeSubjectId knowledgeSubjectId) {
        return WrapperResult.successWrapper(List.of());
    }

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<LearningResult, LearningResultId> getRepository() {
        return null;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<LearningResult> entityClass() {
        return null;
    }
}

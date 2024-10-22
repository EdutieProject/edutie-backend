package com.edutie.backend.infrastructure.persistence.implementation.personalization;

import com.edutie.backend.domain.education.knowledgesubject.identities.KnowledgeSubjectId;
import com.edutie.backend.domain.education.learningrequirement.LearningRequirement;
import com.edutie.backend.domain.personalization.learningresourcedefinition.identities.LearningResourceDefinitionId;
import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.infrastructure.persistence.PersistenceError;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.LearningRequirementRepository;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.LearningResourceDefinitionRepository;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.LearningResultRepository;
import com.edutie.backend.infrastructure.persistence.jpa.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LearningResultPersistenceImplementation implements LearningResultPersistence {
    private final LearningResultRepository learningResultRepository;
    private final StudentRepository studentRepository;
    private final LearningResourceDefinitionRepository learningResourceDefinitionRepository;
    private final LearningRequirementRepository learningRequirementRepository;

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<LearningResult, LearningResultId> getRepository() {
        return learningResultRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<LearningResult> entityClass() {
        return LearningResult.class;
    }

    /**
     * Retrieves latest results associated with given student. Results are ordered from the latest to the older.
     * The retrieved amount is supplied as a result
     *
     * @param studentId student id
     * @param amount    learning result amount
     * @return Wrapper Result of Learning Results
     */
    @Override
    public WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, Integer amount, LocalDateTime maxDate) {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isEmpty())
                return WrapperResult.failureWrapper(PersistenceError.notFound(Student.class));
            List<LearningResult> learningResults;
            if (maxDate != null)
                learningResults = learningResultRepository.findLearningResultsByStudentAndCreatedOnGreaterThanOrderByCreatedOnDesc(
                        student.get(), maxDate, amount == null ? Limit.unlimited() : Limit.of(amount)
                );
            else
                learningResults = learningResultRepository.findLearningResultsByStudentOrderByCreatedOnDesc(student.get(), Limit.of(amount));
            return Result.successWrapper(learningResults);
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(ex));
        }

    }

    /**
     * Provides learning results associated with certain learning resource definition id.
     *
     * @param studentId                    student Id
     * @param learningResourceDefinitionId learning resource definition id
     * @return Learning Result List Wrapper Result
     */
    @Override
    public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByLearningResourceDefinitionId(StudentId studentId, LearningResourceDefinitionId learningResourceDefinitionId) {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            return student
                    .map(value -> WrapperResult.successWrapper(learningResultRepository
                            .findLearningResultsBySolutionSubmissionLearningResourceDefinitionIdAndStudent(learningResourceDefinitionId, value)))
                    .orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(Student.class)));
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(ex));
        }
    }

    /**
     * Provides learning results associated with the L. requirement of certain knowledge subject id created by given student.
     *
     * @param studentId          student id
     * @param knowledgeSubjectId knowledge subject id
     * @return Learning Result List Wrapper Result
     */
    @Override
    public WrapperResult<List<LearningResult>> getLearningResultsOfStudentByKnowledgeSubjectId(StudentId studentId, KnowledgeSubjectId knowledgeSubjectId) {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            if (student.isEmpty())
                return WrapperResult.failureWrapper(PersistenceError.notFound(Student.class));
            List<LearningRequirement> learningRequirements = learningRequirementRepository.findByKnowledgeSubjectId(knowledgeSubjectId);
            return WrapperResult.successWrapper(learningRequirements.stream().flatMap(o ->
                    learningResultRepository.findLearningResultsByLearningRequirement(student.get(), o).stream()
            ).toList());
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(ex));
        }
    }
}

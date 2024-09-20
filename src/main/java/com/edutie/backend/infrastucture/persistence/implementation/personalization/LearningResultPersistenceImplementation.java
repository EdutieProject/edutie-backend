package com.edutie.backend.infrastucture.persistence.implementation.personalization;

import com.edutie.backend.domain.personalization.learningresult.LearningResult;
import com.edutie.backend.domain.personalization.learningresult.identities.LearningResultId;
import com.edutie.backend.domain.personalization.learningresult.persistence.LearningResultPersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.infrastucture.persistence.PersistenceError;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.LearningResultRepository;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.WrapperResult;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LearningResultPersistenceImplementation implements LearningResultPersistence {
    private final LearningResultRepository learningResultRepository;
    private final StudentRepository studentRepository;

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
    public WrapperResult<List<LearningResult>> getLatestResultsOfStudent(StudentId studentId, int amount) {
        try {
            Optional<Student> student = studentRepository.findById(studentId);
            return student
                    .map(value -> WrapperResult.successWrapper(
                            learningResultRepository.findLearningResultsByStudentOrderByCreatedOn(value, Limit.of(amount)))
                    ).orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(Student.class)));
        } catch (Exception ex) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(ex));
        }

    }
}

package com.edutie.backend.infrastucture.persistence.implementation.persistence.profiles;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.learner.student.persistence.StudentPersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import com.edutie.backend.infrastucture.persistence.implementation.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentPersistenceImplementation implements StudentPersistence {
    private final StudentRepository studentRepository;

    /**
     * Retrieves the entity using its identifier. Entity is wrapped in optional, therefore
     * it might not be present based on the identifier presence in the database.
     *
     * @param studentId entity id
     * @return Optional entity
     */
    @Override
    public WrapperResult<Student> getById(StudentId studentId) {
        try {
            Optional<Student> studentOptional = studentRepository.findById(studentId);
            return studentOptional.map(WrapperResult::successWrapper)
                    .orElseGet(() -> WrapperResult.failureWrapper(PersistenceError.notFound(Student.class)));
        } catch (Exception exception) {
            return WrapperResult.failureWrapper(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the entity of the provided id from the database. If the entity is not preset or could not be
     * deleted, does nothing and returns failure result.
     *
     * @param studentId entity id
     * @return Result object
     */
    @Override
    public Result removeById(StudentId studentId) {
        try {
            studentRepository.deleteById(studentId);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Retrieves role profile for the given user. The presence of the role profile should be checked
     * using authorization before using this function.
     *
     * @param userId id of a user
     * @return Role profile of a user
     */
    @Override
    public Student getByAuthorizedUserId(UserId userId) {
        List<Student> students = studentRepository.findStudentsByOwnerUserId(userId);
        return students.stream().findFirst().get();
    }

    /**
     * Persists the provided role profile into the database. If it is already present,
     * updates its state to the provided object's state. Returns result indicating whether
     * the operation was successful or not
     *
     * @param student
     * @return Result object
     */
    @Override
    public Result save(Student student) {
        try {
            studentRepository.save(student);
            return Result.success();
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }

    /**
     * Removes the role for the user of specified id from the database. Success is returned even
     * when the role is not present at first in the database. Failure is returned only when something goes
     * wrong
     *
     * @param userId entity id
     * @return Result object
     */
    @Override
    public Result removeForUser(UserId userId) {
        try {
            Student student = studentRepository.findStudentsByOwnerUserId(userId).getFirst();
            studentRepository.delete(student);
            return Result.success();
        } catch (NoSuchElementException ignored) {
            return Result.failure(PersistenceError.notFound(Student.class));
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }
}

package com.edutie.backend.infrastucture.persistence.implementation.persistence.profiles;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.learner.student.persistence.StudentPersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import com.edutie.backend.infrastucture.persistence.implementation.persistence.common.PersistenceError;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import validation.Result;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class StudentPersistenceImplementation implements StudentPersistence {
    private final StudentRepository studentRepository;

    /**
     * Override this to provide repository for default methods
     *
     * @return crud jpa repository
     */
    @Override
    public JpaRepository<Student, StudentId> getRepository() {
        return studentRepository;
    }

    /**
     * Override this to provide entity class for default methods
     *
     * @return class of persistence entity
     */
    @Override
    public Class<Student> entityClass() {
        return Student.class;
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
        return studentRepository.findByOwnerUserId(userId).get();
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
            Student student = studentRepository.findByOwnerUserId(userId).get();
            studentRepository.delete(student);
            return Result.success();
        } catch (NoSuchElementException ignored) {
            return Result.failure(PersistenceError.notFound(Student.class));
        } catch (Exception exception) {
            return Result.failure(PersistenceError.exceptionEncountered(exception));
        }
    }
}

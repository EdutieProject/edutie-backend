package com.edutie.backend.infrastucture.persistence.implementation.persistence.profiles;

import com.edutie.backend.domain.administration.UserId;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.learner.student.persistence.StudentPersistence;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.StudentRepository;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.common.RoleRepository;
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
    public RoleRepository<Student, StudentId> getRepository() {
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
}

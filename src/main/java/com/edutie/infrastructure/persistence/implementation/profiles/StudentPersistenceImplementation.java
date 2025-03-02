package com.edutie.infrastructure.persistence.implementation.profiles;

import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.identities.StudentId;
import com.edutie.domain.core.learning.student.persistence.StudentPersistence;
import com.edutie.infrastructure.persistence.base.DefaultRolePersistence;
import com.edutie.infrastructure.persistence.implementation.profiles.repositories.StudentRepository;
import com.edutie.infrastructure.persistence.implementation.common.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentPersistenceImplementation
        extends DefaultRolePersistence<Student, StudentId> implements StudentPersistence {
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

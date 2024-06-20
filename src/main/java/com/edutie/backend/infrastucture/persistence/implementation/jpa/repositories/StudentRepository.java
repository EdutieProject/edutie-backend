package com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories;

import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.infrastucture.persistence.implementation.jpa.repositories.common.RoleRepository;

public interface StudentRepository extends RoleRepository<Student, StudentId> {
}

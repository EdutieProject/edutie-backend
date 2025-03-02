package com.edutie.infrastructure.persistence.implementation.profiles.repositories;

import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.identities.StudentId;
import com.edutie.infrastructure.persistence.implementation.common.repositories.RoleRepository;

public interface StudentRepository extends RoleRepository<Student, StudentId> { }

package com.edutie.infrastructure.persistence.jpa.repositories;

import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.identities.StudentId;
import com.edutie.infrastructure.persistence.jpa.repositories.common.RoleRepository;

public interface StudentRepository extends RoleRepository<Student, StudentId> { }

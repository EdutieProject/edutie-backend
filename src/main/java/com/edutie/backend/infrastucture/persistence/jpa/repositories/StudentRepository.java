package com.edutie.backend.infrastucture.persistence.jpa.repositories;

import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.identities.StudentId;
import com.edutie.backend.infrastucture.persistence.jpa.repositories.common.RoleRepository;

public interface StudentRepository extends RoleRepository<Student, StudentId> {
}

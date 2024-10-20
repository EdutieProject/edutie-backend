package com.edutie.backend.domain.personalization.student.persistence;

import com.edutie.backend.domain.common.persistence.RolePersistence;
import com.edutie.backend.domain.personalization.student.Student;
import com.edutie.backend.domain.personalization.student.identities.StudentId;

public interface StudentPersistence extends RolePersistence<Student, StudentId> {
}

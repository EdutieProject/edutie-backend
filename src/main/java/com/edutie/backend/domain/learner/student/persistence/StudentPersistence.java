package com.edutie.backend.domain.learner.student.persistence;

import com.edutie.backend.domain.common.persistence.RolePersistence;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.identities.StudentId;

public interface StudentPersistence extends RolePersistence<Student, StudentId> {
}

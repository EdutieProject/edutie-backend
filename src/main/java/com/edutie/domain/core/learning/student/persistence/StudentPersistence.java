package com.edutie.domain.core.learning.student.persistence;

import com.edutie.domain.core.common.persistence.RolePersistence;
import com.edutie.domain.core.learning.student.Student;
import com.edutie.domain.core.learning.student.identities.StudentId;

public interface StudentPersistence extends RolePersistence<Student, StudentId> {
}

package com.edutie.backend.domain.learner.student.persistence;

import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.domain.learner.student.identities.StudentId;
import com.edutie.backend.domain.common.persistence.PersistenceBase;

public interface StudentPersistence extends PersistenceBase<Student, StudentId> {
    /**
     * Retrieve Student reference based on the user id. It does not return an exceptional type, because
     * by design Student is present for all users.
     * @param userId user identifier
     * @return Student reference
     */
    Student getByUserId(UserId userId);
}

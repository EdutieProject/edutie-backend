package com.edutie.edutiebackend.application.services.management.student;

import com.edutie.edutiebackend.application.data.dto.StudentUpdateDto;
import com.edutie.edutiebackend.application.services.common.GenericRetrievalService;
import com.edutie.edutiebackend.domain.core.common.identities.UserId;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;

public interface StudentService extends GenericRetrievalService<Student, StudentId> {
    Student createStudentForUser(UserId userId);
    boolean resetStudentOfUser(UserId userId);
    Student updateStudentProperties(StudentId studentId, StudentUpdateDto studentUpdateDto);
}

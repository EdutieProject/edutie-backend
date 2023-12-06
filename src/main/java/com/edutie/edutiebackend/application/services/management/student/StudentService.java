package com.edutie.edutiebackend.application.services.management.student;

import com.edutie.edutiebackend.application.services.common.requests.AuthenticatedRequest;
import com.edutie.edutiebackend.application.services.common.servicebase.GenericRetrievalService;
import com.edutie.edutiebackend.application.services.management.student.requests.StudentUpdateRequest;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;

public interface StudentService extends GenericRetrievalService<Student, StudentId> {
    /**
     * Creates a blank student account for a given user.
     * @param request Authenticated request, containing corresponding userId
     * @return Created student account
     * @see Student
     * @see AuthenticatedRequest
     */
    Student createStudentAccount(AuthenticatedRequest request);

    /**
     *  Resets the given student account to the blank state.
     * @param request Authenticated request, containing corresponding userId
     * @return Whether the operation is successful as a boolean
     * @see Student
     * @see AuthenticatedRequest
     */
    boolean resetStudentAccount(AuthenticatedRequest request);

    /**
     * Updates student's properties according to the sent request. Properties which may be updated
     * are those contained within a student update request. Learning Parameters are
     * not modifiable by this service method.
     * @param request Student Update Request containing the information which data to update and how
     * @return Whether the operation is successful as a boolean
     * @see Student
     * @see StudentUpdateRequest
     */
    boolean updateStudentProperties(StudentUpdateRequest request);
}

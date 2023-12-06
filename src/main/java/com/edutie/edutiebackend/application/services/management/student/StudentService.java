package com.edutie.edutiebackend.application.services.management.student;

import com.edutie.edutiebackend.application.services.common.requests.AuthenticatedServiceRequest;
import com.edutie.edutiebackend.application.services.common.servicebase.GenericRetrievalService;
import com.edutie.edutiebackend.application.services.management.student.commands.StudentUpdateCommand;
import com.edutie.edutiebackend.domain.core.student.Student;
import com.edutie.edutiebackend.domain.core.student.identities.StudentId;

public interface StudentService extends GenericRetrievalService<Student, StudentId> {
    /**
     * Creates a blank student account for a given user.
     * @param request Authenticated request, containing corresponding userId
     * @return Created student account
     * @see Student
     * @see AuthenticatedServiceRequest
     */
    Student createStudentAccount(AuthenticatedServiceRequest request);

    /**
     *  Resets the given student account to the blank state.
     * @param request Authenticated request, containing corresponding userId
     * @return Whether the operation is successful as a boolean
     * @see Student
     * @see AuthenticatedServiceRequest
     */
    boolean resetStudentAccount(AuthenticatedServiceRequest request);

    /**
     * Updates student's properties according to the command. Learning Parameters are
     * not modifiable by this service method.
     * @param command Student Update Command containing the information which data to update and how
     * @return Whether the operation is successful as a boolean
     * @see Student
     * @see StudentUpdateCommand
     */
    boolean updateStudentProperties(StudentUpdateCommand command);
}

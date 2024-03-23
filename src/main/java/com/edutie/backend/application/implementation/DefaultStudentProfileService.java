package com.edutie.backend.application.implementation;

import com.edutie.backend.application.implementation.shared.ApplicationError;
import com.edutie.backend.application.profiles.student.StudentProfileService;
import com.edutie.backend.application.profiles.student.commands.ChangeStudentProfilePropertiesCommand;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.infrastucture.persistence.contexts.learner.StudentPersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
public class DefaultStudentProfileService implements StudentProfileService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final StudentPersistenceContext studentPersistenceContext;

    public DefaultStudentProfileService(StudentPersistenceContext studentPersistenceContext) {
        this.studentPersistenceContext = studentPersistenceContext;
    }

    /**
     * Resets the student profile for the specified user.
     *
     * @param userId The identifier of the user.
     * @return A {@code Result} indicating the success or failure of resetting the student profile.
     */
    @Override
    //TODO: createdOn changes - invalid
    public Result resetStudentProfile(UserId userId) {
        LOGGER.info("Resetting student profile for student of id: {}", userId.identifierValue());
        Student studentProfile = Student.create(userId);
        Result result = studentPersistenceContext.save(studentProfile);
        if (result.isSuccess()) {
            LOGGER.info("Student profile reset for student of id {} successful", userId.identifierValue());
            return result;
        }
        LOGGER.info("Could not perform student profile reset for student of id {}", userId.identifierValue());
        return Result.failure(ApplicationError.persistenceOperationError());
    }

    /**
     * Changes the properties of the student profile based on the provided command.
     *
     * @param command The command containing information for changing student profile properties.
     * @return A {@code Result} indicating the success or failure of changing the student profile.
     */
    @Override
    public Result changeStudentProfileProperties(ChangeStudentProfilePropertiesCommand command) {
        LOGGER.info("Changing student profile properties for student of id {}", command.studentId().identifierValue());
        WrapperResult<Student> fetchResult = studentPersistenceContext.getById(command.studentId());
        if (fetchResult.isSuccess()) {
            Student student = fetchResult.getValue();
            student.setSchoolStage(command.studentSchoolStage());
            if (command.studentBirthdate() != null)
                student.setBirthdate(command.studentBirthdate().get());
            LOGGER.info("Successfully changed student profile properties for student of id {}", command.studentId().identifierValue());
            return Result.success();
        }
        LOGGER.info("Could not change student profile properties for student of id {}", command.studentId().identifierValue());
        return Result.failure(ApplicationError.persistenceOperationError());
    }
}

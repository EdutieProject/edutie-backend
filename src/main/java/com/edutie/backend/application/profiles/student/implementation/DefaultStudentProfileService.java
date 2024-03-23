package com.edutie.backend.application.profiles.student.implementation;

import com.edutie.backend.application.profiles.student.StudentProfileService;
import com.edutie.backend.application.profiles.student.commands.ChangeStudentProfilePropertiesCommand;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.learner.student.Student;
import com.edutie.backend.infrastucture.persistence.contexts.learner.StudentPersistenceContext;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

import java.util.Optional;

@Component
public class DefaultStudentProfileService implements StudentProfileService {
    private StudentPersistenceContext studentPersistence;

    /**
     * Resets the student profile for the specified user.
     *
     * @param userId The identifier of the user.
     * @return A {@code Result} indicating the success or failure of resetting the student profile.
     */
    @Override
    public Result resetStudentProfile(UserId userId) {
        Student studentProfile = Student.create(userId);
        //TODO: createdOn changes - invalid
        return studentPersistence.save(studentProfile);
    }

    /**
     * Changes the properties of the student profile based on the provided command.
     *
     * @param command The command containing information for changing student profile properties.
     * @return A {@code Result} indicating the success or failure of changing the student profile.
     */
    @Override
    public Result changeStudentProfileProperties(ChangeStudentProfilePropertiesCommand command) {
        Optional<Student> fetched = studentPersistence.getById(command.studentId());
        if(fetched.isPresent()) {
            Student student = fetched.get();
            student.setSchoolStage(command.studentSchoolStage());
            student.setBirthdate(command.studentBirthdate().get());
        }
        return Result.failure(null);
    }
}

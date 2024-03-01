package com.edutie.backend.application.profiles.student;

import com.edutie.backend.application.profiles.student.commands.ChangeStudentProfilePropertiesCommand;
import com.edutie.backend.domain.common.identities.UserId;
import validation.Result;

/**
 * Service interface for managing student profiles in the educational system.
 */
public interface StudentProfileService {

    /**
     * Resets the student profile for the specified user.
     *
     * @param userId The identifier of the user.
     * @return A {@code Result} indicating the success or failure of resetting the student profile.
     */
    Result resetStudentProfile(UserId userId);

    /**
     * Changes the properties of the student profile based on the provided command.
     *
     * @param command The command containing information for changing student profile properties.
     * @return A {@code Result} indicating the success or failure of changing the student profile.
     */
    Result changeStudentProfileProperties(ChangeStudentProfilePropertiesCommand command);
}

package com.edutie.backend.application.profiles.educator;

import com.edutie.backend.application.profiles.educator.commands.ChangeEducatorTypeCommand;
import com.edutie.backend.domain.common.identities.UserId;
import validation.Result;

/**
 * Service interface for managing educator profiles in the educational system.
 */
public interface EducatorProfileService {

    /**
     * Adds an educator profile for the specified user.
     *
     * @param userId The identifier of the user.
     * @return A {@code Result} indicating the success or failure of adding the educator profile.
     */
    Result addEducatorProfile(UserId userId);

    /**
     * Changes the type of educator for the specified user based on the provided command.
     *
     * @param command The command containing information for changing the educator type.
     * @return A {@code Result} indicating the success or failure of changing the educator type.
     */
    Result changeEducatorType(ChangeEducatorTypeCommand command);

    /**
     * Degrades an educator by removing their educator profile based on the specified user.
     *
     * @param userId The identifier of the user.
     * @return A {@code Result} indicating the success or failure of degrading the educator.
     */
    Result degradeEducator(UserId userId);
}

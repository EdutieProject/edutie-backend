package com.edutie.backend.application.implementation;

import com.edutie.backend.application.implementation.shared.ApplicationError;
import com.edutie.backend.application.profiles.educator.EducatorProfileService;
import com.edutie.backend.application.profiles.educator.commands.ChangeEducatorTypeCommand;
import com.edutie.backend.domain.common.identities.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import validation.Result;

//TODO: implementation needed once the educator persistence exists
@Component
public class MockEducatorProfileService implements EducatorProfileService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Adds an educator profile for the specified user.
     *
     * @param userId The identifier of the user.
     * @return A {@code Result} indicating the success or failure of adding the educator profile.
     */
    @Override
    public Result addEducatorProfile(UserId userId) {
        return Result.failure(ApplicationError.noImplementationProvided());
    }

    /**
     * Changes the type of educator for the specified user based on the provided command.
     *
     * @param command The command containing information for changing the educator type.
     * @return A {@code Result} indicating the success or failure of changing the educator type.
     */
    @Override
    public Result changeEducatorType(ChangeEducatorTypeCommand command) {
        return Result.failure(ApplicationError.noImplementationProvided());
    }

    /**
     * Degrades an educator by removing their educator profile based on the specified user.
     *
     * @param userId The identifier of the user.
     * @return A {@code Result} indicating the success or failure of degrading the educator.
     */
    @Override
    public Result degradeEducator(UserId userId) {
        return Result.failure(ApplicationError.noImplementationProvided());
    }
}

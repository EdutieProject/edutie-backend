package com.edutie.backend.application.profiles.educator.implementation;

import com.edutie.backend.application.profiles.errors.RoleError;
import com.edutie.backend.application.profiles.educator.EducatorProfileService;
import com.edutie.backend.application.profiles.educator.commands.ChangeEducatorTypeCommand;
import com.edutie.backend.domain.common.identities.AdminId;
import com.edutie.backend.domain.common.identities.UserId;
import com.edutie.backend.domain.education.educator.Educator;
import com.edutie.backend.domain.education.educator.persistence.EducatorPersistence;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import validation.Result;
import validation.WrapperResult;

@Component
@RequiredArgsConstructor
public class DefaultEducatorProfileService implements EducatorProfileService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final EducatorPersistence educatorPersistence;

    /**
     * Adds an educator profile for the specified user.
     *
     * @param userId The identifier of the user.
     * @return A {@code Result} indicating the success or failure of adding the educator profile.
     */
    @Override
    public Result addEducatorProfile(UserId userId, AdminId adminId) {
        LOGGER.info("Creating educator profile for user of id {} by admin of id {}", userId.identifierValue(), adminId.identifierValue());
        if (educatorPersistence.getByUserId(userId).isSuccess()){
            LOGGER.info("Educator profile of this user already exists");
            return Result.failure(RoleError.roleAlreadyAssigned());
        }
        Educator educator = Educator.create(userId, adminId);
        educatorPersistence.save(educator);
        return Result.success();
    }

    /**
     * Changes the type of educator for the specified user based on the provided command.
     *
     * @param command The command containing information for changing the educator type.
     * @return A {@code Result} indicating the success or failure of changing the educator type.
     */
    @Override
    public Result changeEducatorType(ChangeEducatorTypeCommand command) {
        Educator educator = educatorPersistence.getById(command.educatorId()).getValue();
        educator.setType(command.educatorType());
        educator.setUpdatedBy(command.updateAdminId());
        return Result.success();
    }

    /**
     * Degrades an educator by removing their educator profile based on the specified user.
     *
     * @param userId The identifier of the user.
     * @return A {@code Result} indicating the success or failure of degrading the educator.
     */
    @Override
    public Result degradeEducator(UserId userId) {
        WrapperResult<Educator> educatorSearchResult = educatorPersistence.getByUserId(userId);
        if (educatorSearchResult.isFailure())
            return Result.failure(educatorSearchResult.getErrors().getFirst());
        educatorPersistence.removeById(educatorSearchResult.getValue().getId());
        return Result.success();
    }
}
